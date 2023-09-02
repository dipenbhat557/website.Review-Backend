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
@RequestMapping("/reviews")
public class ReviewController {

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

    // creating review
    @PostMapping("/{softwareId}")
    @Operation(security = { @SecurityRequirement(name = "BearerJWT") })
    public ResponseEntity<ReviewDto> createReview(@RequestBody ReviewRequest reviewRequest, Principal principal,
            @PathVariable int softwareId) {
        System.out.println("Inside creation of review");
        System.out.println(principal.getName());
        return new ResponseEntity<ReviewDto>(this.reviewService.create(principal.getName(), reviewRequest, softwareId),
                HttpStatus.CREATED);
    }

    // getting review by id
    @GetMapping("/{reviewId}")
    @Operation(security = { @SecurityRequirement(name = "BearerJWT") })
    public ResponseEntity<ReviewDto> viewById(@PathVariable int reviewId) {
        return new ResponseEntity<ReviewDto>(this.reviewService.viewById(reviewId), HttpStatus.OK);
    }

    // getting review by user
    @GetMapping("/user")
    @Operation(security = { @SecurityRequirement(name = "BearerJWT") })
    public ResponseEntity<ReviewResponse> viewByUser(Principal principal,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.pageSizeString, required = false) int pageSize,
            @RequestParam(value = "pageNumber", defaultValue = AppConstants.pageNumberString, required = false) int pageNumber) {
        return new ResponseEntity<ReviewResponse>(
                this.reviewService.viewByUser(principal.getName(), pageNumber, pageSize), HttpStatus.OK);
    }

    // deleting the review
    @DeleteMapping("/{reviewId}")
    @Operation(security = { @SecurityRequirement(name = "BearerJWT") })
    public ResponseEntity<String> delete(@PathVariable int reviewId) {
        this.reviewService.delete(reviewId);
        return new ResponseEntity<String>("Successffully deleted...", HttpStatus.OK);
    }

    @GetMapping
    @Operation(security = { @SecurityRequirement(name = "BearerJWT") })
    public ResponseEntity<ReviewResponse> viewAll(
            @RequestParam(value = "pageSize", defaultValue = AppConstants.pageSizeString, required = false) int pageSize,
            @RequestParam(value = "pageNumber", defaultValue = AppConstants.pageNumberString, required = false) int pageNumber) {
        return new ResponseEntity<ReviewResponse>(
                this.reviewService.viewAll(pageNumber, pageSize), HttpStatus.OK);
    }

    // filtering reviews by organizationSize
    @PostMapping("/filterBySize")
    @Operation(security = { @SecurityRequirement(name = "BearerJWT") })
    public ResponseEntity<ReviewResponse> filterBySize(@RequestBody FilterByOrganizationSizeRequest request,
            @RequestParam(value = "pageNumber", defaultValue = AppConstants.pageNumberString, required = false) int pageNumber,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.pageSizeString, required = false) int pageSize) {
        ReviewResponse reviewResponse = this.reviewService.filterReviewsByOrganizationSize(request.getMinSize(),
                request.getMaxSize(), pageNumber, pageSize);
        return new ResponseEntity<ReviewResponse>(reviewResponse, HttpStatus.OK);
    }

    // filtering by rating
    @GetMapping("/filterByRating/{rating}")
    @Operation(security = { @SecurityRequirement(name = "BearerJWT") })
    public ResponseEntity<ReviewResponse> filterByRating(@PathVariable int rating,
            @RequestParam(value = "pageNumber", defaultValue = AppConstants.pageNumberString, required = false) int pageNumber,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.pageSizeString, required = false) int pageSize) {

        ReviewResponse response = this.reviewService.viewByRating(rating, pageNumber, pageSize);

        return new ResponseEntity<ReviewResponse>(response, HttpStatus.OK);
    }

    // filtering by purpose
    @PostMapping("/filterByPurpose")
    @Operation(security = { @SecurityRequirement(name = "BearerJWT") })
    public ResponseEntity<ReviewResponse> filterByPurpose(@RequestBody FilterByPurposeRequest request,
            @RequestParam(value = "pageNumber", defaultValue = AppConstants.pageNumberString, required = false) int pageNumber,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.pageSizeString, required = false) int pageSize) {

        ReviewResponse response = this.reviewService.viewByPurpose(request.getPurpose(), pageNumber, pageSize);

        return new ResponseEntity<ReviewResponse>(response, HttpStatus.OK);
    }

    // filtering by user role
    @GetMapping("/filterByUserRole/{userRole}")
    @Operation(security = { @SecurityRequirement(name = "BearerJWT") })
    public ResponseEntity<ReviewResponse> filterByUserRole(@PathVariable String userRole,
            @RequestParam(value = "pageNumber", defaultValue = AppConstants.pageNumberString, required = false) int pageNumber,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.pageSizeString, required = false) int pageSize) {

        ReviewResponse response = this.reviewService.viewByUserRole(userRole, pageNumber, pageSize);

        return new ResponseEntity<ReviewResponse>(response, HttpStatus.OK);
    }
}