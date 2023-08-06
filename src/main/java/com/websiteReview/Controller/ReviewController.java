package com.websiteReview.Controller;

import java.security.Principal;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import com.websiteReview.Helper.AppConstants;
import com.websiteReview.Helper.FilterByOrganizationSizeRequest;
import com.websiteReview.Helper.FilterByPurposeRequest;
import com.websiteReview.Helper.ReviewResponse;
import com.websiteReview.ServiceImpl.AboutReviewProductServiceImpl;
import com.websiteReview.ServiceImpl.AboutReviewUserServiceImpl;
import com.websiteReview.ServiceImpl.FileUploadServiceImpl;
import com.websiteReview.ServiceImpl.ReviewServiceImpl;

@RestController
@RequestMapping("/review")
public class ReviewController {

    @Autowired
    private AboutReviewUserServiceImpl aboutReviewUserService;

    @Autowired
    private FileUploadServiceImpl fileUploadService;

    @Autowired
    private AboutReviewProductServiceImpl aboutReviewProductService;

    @Autowired
    private ReviewServiceImpl reviewService;

    private String imagePath = "src/review/currentUser/screenshot";

    // creating review
    @PostMapping("/create/{softwareId}")
    public ResponseEntity<ReviewDto> createReview(@RequestBody ReviewDto reviewDto, Principal principal,
            @PathVariable int softwareId) {
        System.out.println("Inside creation of review");
        System.out.println(principal.getName());
        return new ResponseEntity<ReviewDto>(this.reviewService.create(principal.getName(), reviewDto, softwareId),
                HttpStatus.CREATED);
    }

    // getting review by id
    @GetMapping("/viewById/{reviewId}")
    public ResponseEntity<ReviewDto> viewById(@PathVariable int reviewId) {
        return new ResponseEntity<ReviewDto>(this.reviewService.viewById(reviewId), HttpStatus.OK);
    }

    // getting review by user
    @GetMapping("/viewByUser")
    public ResponseEntity<ReviewResponse> viewByUser(Principal principal,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.pageSizeString, required = false) int pageSize,
            @RequestParam(value = "pageNumber", defaultValue = AppConstants.pageNumberString, required = false) int pageNumber) {
        return new ResponseEntity<ReviewResponse>(
                this.reviewService.viewByUser(principal.getName(), pageSize, pageNumber), HttpStatus.OK);
    }

    // deleting the review
    @GetMapping("/delete/{reviewId}")
    public ResponseEntity<String> delete(@PathVariable int reviewId) {
        this.reviewService.delete(reviewId);
        return new ResponseEntity<String>("Successffully deleted...", HttpStatus.OK);
    }

    // filtering reviews by organizationSize
    @PostMapping("/filterBySize")
    public ResponseEntity<ReviewResponse> filterBySize(@RequestBody FilterByOrganizationSizeRequest request,
            @RequestParam(value = "pageNumber", defaultValue = AppConstants.pageNumberString, required = false) int pageNumber,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.pageSizeString, required = false) int pageSize) {
        ReviewResponse reviewResponse = this.reviewService.filterReviewsByOrganizationSize(request.getMinSize(),
                request.getMaxSize(), pageNumber, pageSize);
        return new ResponseEntity<ReviewResponse>(reviewResponse, HttpStatus.OK);
    }

    // filtering by rating
    @GetMapping("/filterByRating/{rating}")
    public ResponseEntity<ReviewResponse> filterByRating(@PathVariable int rating,
            @RequestParam(value = "pageNumber", defaultValue = AppConstants.pageNumberString, required = false) int pageNumber,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.pageSizeString, required = false) int pageSize) {

        ReviewResponse response = this.reviewService.viewByRating(rating, pageNumber, pageSize);

        return new ResponseEntity<ReviewResponse>(response, HttpStatus.OK);
    }

    // filtering by purpose
    @PostMapping("/filterByPurpose")
    public ResponseEntity<ReviewResponse> filterByPurpose(@RequestBody FilterByPurposeRequest request,
            @RequestParam(value = "pageNumber", defaultValue = AppConstants.pageNumberString, required = false) int pageNumber,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.pageSizeString, required = false) int pageSize) {

        ReviewResponse response = this.reviewService.viewByPurpose(request.getPurpose(), pageNumber, pageSize);

        return new ResponseEntity<ReviewResponse>(response, HttpStatus.OK);
    }

    // filtering by user role
    @GetMapping("/filterByUserRole/{userRole}")
    public ResponseEntity<ReviewResponse> filterByUserRole(@PathVariable String userRole,
            @RequestParam(value = "pageNumber", defaultValue = AppConstants.pageNumberString, required = false) int pageNumber,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.pageSizeString, required = false) int pageSize) {

        ReviewResponse response = this.reviewService.viewByUserRole(userRole, pageNumber, pageSize);

        return new ResponseEntity<ReviewResponse>(response, HttpStatus.OK);
    }

    //
    //
    //
    // creating the details about the product while writing review
    //
    //
    //
    @PostMapping("/product/create/{reviewId}")
    public ResponseEntity<AboutReviewProductDto> createAboutProduct(@PathVariable int reviewId,
            @RequestBody AboutReviewProductDto aboutReviewProductDto) {
        return new ResponseEntity<AboutReviewProductDto>(
                this.aboutReviewProductService.create(reviewId, aboutReviewProductDto), HttpStatus.CREATED);
    }

    // getting the product details using review id
    @GetMapping("/product/viewByReview/{reviewId}")
    public ResponseEntity<AboutReviewProductDto> viewProductByReview(@PathVariable int reviewId) {
        return new ResponseEntity<AboutReviewProductDto>(this.aboutReviewProductService.viewByReview(reviewId),
                HttpStatus.OK);
    }

    // getting the product details using review product id
    @GetMapping("/product/viewById/{productId}")
    public ResponseEntity<AboutReviewProductDto> viewProductById(@PathVariable int productId) {
        return new ResponseEntity<AboutReviewProductDto>(this.aboutReviewProductService.viewById(productId),
                HttpStatus.OK);
    }

    //
    //
    //
    // creating about user writing the review
    //
    //
    @PostMapping("/user/create/{reviewId}")
    public ResponseEntity<AboutReviewUserDto> createAboutReviewUser(@PathVariable int reviewId,
            @RequestBody AboutReviewUserDto aboutReviewUserDto) {
        return new ResponseEntity<AboutReviewUserDto>(this.aboutReviewUserService.create(reviewId, aboutReviewUserDto),
                HttpStatus.CREATED);
    }

    // viewing the user details writing the review using review id
    @GetMapping("/user/viewByReview/{reviewId}")
    public ResponseEntity<AboutReviewUserDto> viewByReview(@PathVariable int reviewId) {
        return new ResponseEntity<AboutReviewUserDto>(this.aboutReviewUserService.viewByReview(reviewId),
                HttpStatus.OK);
    }

    // getting the user details using review user id
    @GetMapping("/user/viewById/{userId}")
    public ResponseEntity<AboutReviewUserDto> viewUserById(@PathVariable int userId) {
        return new ResponseEntity<AboutReviewUserDto>(this.aboutReviewUserService.viewById(userId), HttpStatus.OK);
    }

    // uploading the screenshot for confirmation of user as a current user of the
    // notion
    @PostMapping("/uploadScreenshot/{reviewUserId}")
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
}
