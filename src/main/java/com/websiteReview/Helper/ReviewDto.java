package com.websiteReview.Helper;

import com.websiteReview.Model.AboutReviewProduct;
import com.websiteReview.Model.AboutReviewUser;

public class ReviewDto {

    private int reviewId;
    private int title;
    private String whatYouLike;
    private String whatYouDislike;
    private String userRole;
    private double rating;
    private int noOfResponses;
    private UserDto userDto;
    private AboutReviewUser aboutReviewUser;
    private AboutReviewProduct aboutReviewProduct;
    public int getReviewId() {
        return reviewId;
    }
    public void setReviewId(int reviewId) {
        this.reviewId = reviewId;
    }
    public int getTitle() {
        return title;
    }
    public void setTitle(int title) {
        this.title = title;
    }
    public String getWhatYouLike() {
        return whatYouLike;
    }
    public void setWhatYouLike(String whatYouLike) {
        this.whatYouLike = whatYouLike;
    }
    public String getWhatYouDislike() {
        return whatYouDislike;
    }
    public void setWhatYouDislike(String whatYouDislike) {
        this.whatYouDislike = whatYouDislike;
    }
    public String getUserRole() {
        return userRole;
    }
    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }
    public double getRating() {
        return rating;
    }
    public void setRating(double rating) {
        this.rating = rating;
    }
    public int getNoOfResponses() {
        return noOfResponses;
    }
    public void setNoOfResponses(int noOfResponses) {
        this.noOfResponses = noOfResponses;
    }
    public UserDto getUserDto() {
        return userDto;
    }
    public void setUserDto(UserDto userDto) {
        this.userDto = userDto;
    }
    public AboutReviewUser getAboutReviewUser() {
        return aboutReviewUser;
    }
    public void setAboutReviewUser(AboutReviewUser aboutReviewUser) {
        this.aboutReviewUser = aboutReviewUser;
    }
    public AboutReviewProduct getAboutReviewProduct() {
        return aboutReviewProduct;
    }
    public void setAboutReviewProduct(AboutReviewProduct aboutReviewProduct) {
        this.aboutReviewProduct = aboutReviewProduct;
    }
    public ReviewDto() {
    }
    public ReviewDto(int reviewId, int title, String whatYouLike, String whatYouDislike, String userRole, double rating,
            int noOfResponses, UserDto userDto, AboutReviewUser aboutReviewUser,
            AboutReviewProduct aboutReviewProduct) {
        this.reviewId = reviewId;
        this.title = title;
        this.whatYouLike = whatYouLike;
        this.whatYouDislike = whatYouDislike;
        this.userRole = userRole;
        this.rating = rating;
        this.noOfResponses = noOfResponses;
        this.userDto = userDto;
        this.aboutReviewUser = aboutReviewUser;
        this.aboutReviewProduct = aboutReviewProduct;
    }
    @Override
    public String toString() {
        return "ReviewDto [reviewId=" + reviewId + ", title=" + title + ", whatYouLike=" + whatYouLike
                + ", whatYouDislike=" + whatYouDislike + ", userRole=" + userRole + ", rating=" + rating
                + ", noOfResponses=" + noOfResponses + ", userDto=" + userDto + ", aboutReviewUser=" + aboutReviewUser
                + ", aboutReviewProduct=" + aboutReviewProduct + "]";
    }

    
    
}
