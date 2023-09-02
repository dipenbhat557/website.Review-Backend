package com.websiteReview.Controller;

import java.security.Principal;
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

import com.websiteReview.Dtos.AboutReviewProductDto;
import com.websiteReview.Dtos.AboutReviewUserDto;
import com.websiteReview.Dtos.ReviewDto;
import com.websiteReview.Helper.AboutReviewProductRequest;
import com.websiteReview.Helper.AboutReviewUserRequest;
import com.websiteReview.Helper.AppConstants;
import com.websiteReview.Helper.FilterByOrganizationSizeRequest;
import com.websiteReview.Helper.FilterByPurposeRequest;
import com.websiteReview.Helper.ReviewRequest;
import com.websiteReview.Helper.ReviewResponse;
import com.websiteReview.ServiceImpl.AboutReviewProductServiceImpl;
import com.websiteReview.ServiceImpl.AboutReviewUserServiceImpl;
import com.websiteReview.ServiceImpl.FileUploadServiceImpl;
import com.websiteReview.ServiceImpl.ReviewServiceImpl;
import com.websiteReview.ServiceImpl.SoftwareServiceImpl;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("/reviews/users")
public class ReviewUserController {

    @Autowired
    private AboutReviewUserServiceImpl aboutReviewUserService;

    @Autowired
    private FileUploadServiceImpl fileUploadService;

    @Autowired
    private AboutReviewProductServiceImpl aboutReviewProductService;

    @Autowired
    private ReviewServiceImpl reviewService;

    @Autowired
    private SoftwareServiceImpl softwareService;

    private String imagePath = "src/review/currentUser/screenshot";

    @PostMapping("/{reviewId}")
    @Operation(security = { @SecurityRequirement(name = "BearerJWT") })
    public ResponseEntity<AboutReviewUserDto> createAboutReviewUser(@PathVariable int reviewId,
            @RequestBody AboutReviewUserRequest aboutReviewUserRequest) {
        return new ResponseEntity<AboutReviewUserDto>(
                this.aboutReviewUserService.create(reviewId, aboutReviewUserRequest),
                HttpStatus.CREATED);
    }

    // viewing the user details writing the review using review id
    @GetMapping("/review/{reviewId}")
    @Operation(security = { @SecurityRequirement(name = "BearerJWT") })
    public ResponseEntity<AboutReviewUserDto> viewByReview(@PathVariable int reviewId) {
        return new ResponseEntity<AboutReviewUserDto>(this.aboutReviewUserService.viewByReview(reviewId),
                HttpStatus.OK);
    }

    // getting the user details using review user id
    @GetMapping("/{userId}")
    @Operation(security = { @SecurityRequirement(name = "BearerJWT") })
    public ResponseEntity<AboutReviewUserDto> viewUserById(@PathVariable int userId) {
        return new ResponseEntity<AboutReviewUserDto>(this.aboutReviewUserService.viewById(userId), HttpStatus.OK);
    }

    @DeleteMapping("/{aboutReviewUserId}")
    @Operation(security = { @SecurityRequirement(name = "BearerJWT") })
    public ResponseEntity<String> deleteAboutReviewUser(@PathVariable int aboutReviewUserId) {
        this.aboutReviewUserService.delete(aboutReviewUserId);
        return new ResponseEntity<String>("Deleted successfully..", HttpStatus.OK);
    }

    // uploading the screenshot for confirmation of user as a current user of the
    // notion
    @PostMapping("/uploadScreenshot/{reviewUserId}")
    @Operation(security = { @SecurityRequirement(name = "BearerJWT") })
    public ResponseEntity<?> uploadScreenshotProof(@PathVariable int reviewUserId,
            @RequestParam("screenshot") MultipartFile file) {
        AboutReviewUserDto aboutReviewUserDto = this.aboutReviewUserService.viewById(reviewUserId);
        String imageName = null;

        try {

            imageName = this.fileUploadService.uploadImage(imagePath, file);
            aboutReviewUserDto.setScreenshotName(imageName);

            AboutReviewUserDto updatedUser = this.aboutReviewUserService.update(reviewUserId, aboutReviewUserDto);

            return new ResponseEntity<>(updatedUser, HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(Map.of("Message", "Couldn't upload the image....."),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/viewPurposes/{softwareId}")
    @Operation(security = { @SecurityRequirement(name = "BearerJWT") })
    public ResponseEntity<List<String>> viewPurposes(@PathVariable int softwareId) {
        return new ResponseEntity<List<String>>(this.softwareService.viewAllPurposes(softwareId), HttpStatus.OK);
    }
}
