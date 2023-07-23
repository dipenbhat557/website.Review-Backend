package com.websiteReview.Controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.web.exchanges.HttpExchange.Principal;
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

import com.websiteReview.Helper.AboutReviewProductDto;
import com.websiteReview.Helper.AboutReviewUserDto;
import com.websiteReview.Helper.ReviewDto;
import com.websiteReview.Service.AboutReviewProductService;
import com.websiteReview.Service.AboutReviewUserService;
import com.websiteReview.Service.FileUploadService;
import com.websiteReview.Service.ReviewService;

@RestController
@RequestMapping("/review")
public class ReviewController {

    @Autowired
    private AboutReviewUserService aboutReviewUserService;

    @Autowired
    private FileUploadService fileUploadService;

    @Autowired
    private AboutReviewProductService aboutReviewProductService;

    @Autowired
    private ReviewService reviewService;

    private String imagePath = "src/review/currentUser/screenshot";

    // creating review
    @PostMapping("/createReview")
    public ResponseEntity<ReviewDto> createReview(@RequestBody ReviewDto reviewDto, Principal principal) {
        return new ResponseEntity<ReviewDto>(this.reviewService.create(principal.getName(), reviewDto),
                HttpStatus.CREATED);
    }

    //getting review by id
    @GetMapping("/viewReviewById/{reviewId}")
    public ResponseEntity<ReviewDto> viewById(@PathVariable int reviewId){
        return new ResponseEntity<ReviewDto>(this.reviewService.viewById(reviewId), HttpStatus.OK);
    }

    //getting review by user
    @GetMapping("/viewReviewsByUser")
    public ResponseEntity<List<ReviewDto>> viewByUser(Principal principal){
        return new ResponseEntity<List<ReviewDto>>(this.reviewService.viewByUser(principal.getName()), HttpStatus.OK);
    }

    // creating the details about the product while writing review
    @PostMapping("/createProduct/{reviewId}")
    public ResponseEntity<AboutReviewProductDto> createAboutProduct(@PathVariable int reviewId,
            @RequestBody AboutReviewProductDto aboutReviewProductDto) {
        return new ResponseEntity<AboutReviewProductDto>(
                this.aboutReviewProductService.create(reviewId, aboutReviewProductDto), HttpStatus.CREATED);
    }

    // getting the product details using review id
    @GetMapping("/viewProductByReview/{reviewId}")
    public ResponseEntity<AboutReviewProductDto> viewProductByReview(@PathVariable int reviewId) {
        return new ResponseEntity<AboutReviewProductDto>(this.aboutReviewProductService.viewByReview(reviewId),
                HttpStatus.OK);
    }

    // getting the product details using review product id
    @GetMapping("/viewProductById/{productId}")
    public ResponseEntity<AboutReviewProductDto> viewProductById(@PathVariable int productId) {
        return new ResponseEntity<AboutReviewProductDto>(this.aboutReviewProductService.viewById(productId),
                HttpStatus.OK);
    }

    // creating about user writing the review
    @PostMapping("/createUser/{reviewId}")
    public ResponseEntity<AboutReviewUserDto> createAboutReviewUser(@PathVariable int reviewId,
            @RequestBody AboutReviewUserDto aboutReviewUserDto) {
        return new ResponseEntity<AboutReviewUserDto>(this.aboutReviewUserService.create(reviewId, aboutReviewUserDto),
                HttpStatus.CREATED);
    }

    // viewing the user details writing the review using review id
    @GetMapping("/viewUserByReview/{reviewId}")
    public ResponseEntity<AboutReviewUserDto> viewByReview(@PathVariable int reviewId) {
        return new ResponseEntity<AboutReviewUserDto>(this.aboutReviewUserService.viewByReview(reviewId),
                HttpStatus.OK);
    }

    // getting the user details using review user id
    @GetMapping("/viewUserById/{userId}")
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
