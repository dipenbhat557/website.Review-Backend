package com.websiteReview.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.websiteReview.Dtos.AboutReviewProductDto;
import com.websiteReview.Helper.AboutReviewProductRequest;
import com.websiteReview.ServiceImpl.AboutReviewProductServiceImpl;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("/reviews/products")
public class ReviewProductController {

    @Autowired
    private AboutReviewProductServiceImpl aboutReviewProductService;

    // Create information about a product for a specific review
    @PostMapping("/{reviewId}")
    @Operation(security = { @SecurityRequirement(name = "BearerJWT") })
    public ResponseEntity<AboutReviewProductDto> createAboutProduct(@PathVariable int reviewId,
            @RequestBody AboutReviewProductRequest aboutReviewProductRequest) {
        return new ResponseEntity<AboutReviewProductDto>(
                this.aboutReviewProductService.create(reviewId, aboutReviewProductRequest), HttpStatus.CREATED);
    }

    // Get product details associated with a review using the review ID
    @GetMapping("/review/{reviewId}")
    @Operation(security = { @SecurityRequirement(name = "BearerJWT") })
    public ResponseEntity<AboutReviewProductDto> viewProductByReview(@PathVariable int reviewId) {
        return new ResponseEntity<AboutReviewProductDto>(this.aboutReviewProductService.viewByReview(reviewId),
                HttpStatus.OK);
    }

    // Get product details by product ID
    @GetMapping("/{productId}")
    @Operation(security = { @SecurityRequirement(name = "BearerJWT") })
    public ResponseEntity<AboutReviewProductDto> viewProductById(@PathVariable int productId) {
        return new ResponseEntity<AboutReviewProductDto>(this.aboutReviewProductService.viewById(productId),
                HttpStatus.OK);
    }

    // Delete product information by product ID
    @DeleteMapping("/{productId}")
    @Operation(security = { @SecurityRequirement(name = "BearerJWT") })
    public ResponseEntity<String> deleteAboutReviewProduct(@PathVariable int productId) {
        this.aboutReviewProductService.delete(productId);
        return new ResponseEntity<String>("Deleted successfully..", HttpStatus.OK);
    }
}
