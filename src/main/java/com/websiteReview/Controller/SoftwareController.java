package com.websiteReview.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.websiteReview.Dtos.SoftwareDto;
import com.websiteReview.Helper.AppConstants;
import com.websiteReview.Helper.SoftwareRequest;
import com.websiteReview.Helper.SoftwareResponse;
import com.websiteReview.ServiceImpl.FileUploadServiceImpl;
import com.websiteReview.ServiceImpl.SoftwareServiceImpl;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("/software")
public class SoftwareController {

    @Autowired
    private SoftwareServiceImpl softwareService;

    @Autowired
    private FileUploadServiceImpl fileUploadService;

    private String path = "src/software/screenshots";
    private String videoPath = "src/software/videos";

    // Create a new software entry
    @PostMapping("/{subCategoryId}")
    @Operation(security = { @SecurityRequirement(name = "BearerJWT") })
    public ResponseEntity<SoftwareDto> create(@RequestBody SoftwareRequest softwareRequest,
            @PathVariable int subCategoryId) {
        return new ResponseEntity<SoftwareDto>(this.softwareService.create(softwareRequest, subCategoryId),
                HttpStatus.CREATED);
    }

    // Get software details by ID
    @GetMapping("/{softwareId}")
    @Operation(security = { @SecurityRequirement(name = "BearerJWT") })
    public ResponseEntity<SoftwareDto> viewById(@PathVariable int softwareId) {
        return new ResponseEntity<SoftwareDto>(this.softwareService.viewById(softwareId), HttpStatus.OK);
    }

    // Get all software entries
    @GetMapping
    @Operation(security = { @SecurityRequirement(name = "BearerJWT") })
    public ResponseEntity<List<SoftwareDto>> viewAllSoftwares() {
        return new ResponseEntity<List<SoftwareDto>>(this.softwareService.viewAll(), HttpStatus.OK);
    }

    // Delete a software entry by ID
    @DeleteMapping("/{softwareId}")
    @Operation(security = { @SecurityRequirement(name = "BearerJWT") })
    public ResponseEntity<String> delete(@PathVariable int softwareId) {
        this.softwareService.delete(softwareId);
        return new ResponseEntity<String>("Successfully deleted...", HttpStatus.OK);
    }

    // Upload software files (screenshots, video, profile image)
    @PostMapping("/uploadFiles/{softwareId}")
    @Operation(security = { @SecurityRequirement(name = "BearerJWT") })
    public ResponseEntity<?> uploadScreenshots(@PathVariable int softwareId,
            @RequestParam(value = "profileImage", required = false) MultipartFile profileImage,
            @RequestParam(value = "screenshots", required = false) List<MultipartFile> screenshots,
            @RequestParam(value = "video", required = false) MultipartFile video) {

        SoftwareDto softwareDto = this.softwareService.viewById(softwareId);
        List<String> screenshotNames = new ArrayList<>();
        String videoName = null;
        String profileImageName = null;

        try {
            screenshotNames = this.fileUploadService.uploadImages(path, screenshots);
            videoName = this.fileUploadService.uploadImage(videoPath, video);
            profileImageName = this.fileUploadService.uploadImage(path, profileImage);

            softwareDto.setScreenshots(screenshotNames);
            softwareDto.setVideoName(videoName);
            softwareDto.setProfileImageName(profileImageName);

            SoftwareDto updatedSoftwareDto = this.softwareService.update(softwareId, softwareDto);

            return new ResponseEntity<>(updatedSoftwareDto, HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(Map.of("Message", "Couldn't upload the screenshots or video....."),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Filter software by rating
    @GetMapping("/filterByRating/{rating}")
    @Operation(security = { @SecurityRequirement(name = "BearerJWT") })
    public ResponseEntity<SoftwareResponse> filterByRating(@PathVariable int rating,
            @RequestParam(value = "pageNumber", defaultValue = AppConstants.pageNumberString, required = false) int pageNumber,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.pageSizeString, required = false) int pageSize) {

        return new ResponseEntity<SoftwareResponse>(this.softwareService.viewByRating(rating, pageNumber, pageSize),
                HttpStatus.ACCEPTED);
    }

    // Filter software by category
    @GetMapping("/filterByCategory/{categoryId}")
    @Operation(security = { @SecurityRequirement(name = "BearerJWT") })
    public ResponseEntity<SoftwareResponse> filterByCategory(@PathVariable int categoryId,
            @RequestParam(value = "pageNumber", defaultValue = AppConstants.pageNumberString, required = false) int pageNumber,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.pageSizeString, required = false) int pageSize) {

        return new ResponseEntity<SoftwareResponse>(
                this.softwareService.viewBySubCategory(categoryId, pageNumber, pageSize), HttpStatus.ACCEPTED);
    }

    // Filter software by segment
    @GetMapping("/filterBySegment/{sizeId}")
    @Operation(security = { @SecurityRequirement(name = "BearerJWT") })
    public ResponseEntity<SoftwareResponse> filterBySegment(@PathVariable int sizeId,
            @RequestParam(value = "pageNumber", defaultValue = AppConstants.pageNumberString, required = false) int pageNumber,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.pageSizeString, required = false) int pageSize) {

        return new ResponseEntity<SoftwareResponse>(this.softwareService.viewBySegment(sizeId, pageNumber, pageSize),
                HttpStatus.ACCEPTED);
    }
}