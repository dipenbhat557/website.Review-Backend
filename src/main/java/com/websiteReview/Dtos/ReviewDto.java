package com.websiteReview.Dtos;

import com.websiteReview.Model.AboutReviewProduct;
import com.websiteReview.Model.AboutReviewUser;
import com.websiteReview.Model.Software;

public class ReviewDto {

    private int reviewId;
    private String title;
    private String whatYouLike;
    private String whatYouDislike;
    private String userRole;
    private double rating;
    private UserDto userDto;
    private AboutReviewUser aboutReviewUser;
    private AboutReviewProduct aboutReviewProduct;
    private Software software;

    public int getReviewId() {
        return reviewId;
    }
    public void setReviewId(int reviewId) {
        this.reviewId = reviewId;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
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
    
    
    public ReviewDto(int reviewId, String title, String whatYouLike, String whatYouDislike, String userRole,
            double rating, UserDto userDto, AboutReviewUser aboutReviewUser, AboutReviewProduct aboutReviewProduct,
            Software software) {
        this.reviewId = reviewId;
        this.title = title;
        this.whatYouLike = whatYouLike;
        this.whatYouDislike = whatYouDislike;
        this.userRole = userRole;
        this.rating = rating;
        this.userDto = userDto;
        this.aboutReviewUser = aboutReviewUser;
        this.aboutReviewProduct = aboutReviewProduct;
        this.software = software;
    }

    public Software getSoftware() {
        return software;
    }
    public void setSoftware(Software software) {
        this.software = software;
    }
    @Override
    public String toString() {
        return "ReviewDto [reviewId=" + reviewId + ", title=" + title + ", whatYouLike=" + whatYouLike
                + ", whatYouDislike=" + whatYouDislike + ", userRole=" + userRole + ", rating=" + rating + ", userDto="
                + userDto + ", aboutReviewUser=" + aboutReviewUser + ", aboutReviewProduct=" + aboutReviewProduct
                + ", software=" + software + "]";
    }

    
    
}
