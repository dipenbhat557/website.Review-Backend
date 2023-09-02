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
@RequestMapping("/reviews/products")
public class ReviewProductController {

    @Autowired
    private AboutReviewProductServiceImpl aboutReviewProductService;

    @PostMapping("/{reviewId}")
    @Operation(security = { @SecurityRequirement(name = "BearerJWT") })
    public ResponseEntity<AboutReviewProductDto> createAboutProduct(@PathVariable int reviewId,
            @RequestBody AboutReviewProductRequest aboutReviewProductRequest) {
        return new ResponseEntity<AboutReviewProductDto>(
                this.aboutReviewProductService.create(reviewId, aboutReviewProductRequest), HttpStatus.CREATED);
    }

    // getting the product details using review id
    @GetMapping("/review/{reviewId}")
    @Operation(security = { @SecurityRequirement(name = "BearerJWT") })
    public ResponseEntity<AboutReviewProductDto> viewProductByReview(@PathVariable int reviewId) {
        return new ResponseEntity<AboutReviewProductDto>(this.aboutReviewProductService.viewByReview(reviewId),
                HttpStatus.OK);
    }

    // getting the product details using review product id
    @GetMapping("/{productId}")
    @Operation(security = { @SecurityRequirement(name = "BearerJWT") })
    public ResponseEntity<AboutReviewProductDto> viewProductById(@PathVariable int productId) {
        return new ResponseEntity<AboutReviewProductDto>(this.aboutReviewProductService.viewById(productId),
                HttpStatus.OK);
    }

    @DeleteMapping("/{productId}")
    @Operation(security = { @SecurityRequirement(name = "BearerJWT") })
    public ResponseEntity<String> deleteAboutReviewProduct(@PathVariable int productId) {
        this.aboutReviewProductService.delete(productId);
        return new ResponseEntity<String>("Deleted successfully..", HttpStatus.OK);
    }
}
