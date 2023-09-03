package com.websiteReview.Dtos;

public class ReviewDto {

    private int reviewId;
    private String title;
    private String whatYouLike;
    private String whatYouDislike;
    private String userRole;
    private double rating;

    private int userId;
    private AboutReviewUserDto aboutReviewUserDto;
    private AboutReviewProductDto aboutReviewProductDto;
    private int softwareId;

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

    public AboutReviewUserDto getAboutReviewUserDto() {
        return aboutReviewUserDto;
    }

    public void setAboutReviewUserDto(AboutReviewUserDto aboutReviewUserDto) {
        this.aboutReviewUserDto = aboutReviewUserDto;
    }

    public AboutReviewProductDto getAboutReviewProductDto() {
        return aboutReviewProductDto;
    }

    public void setAboutReviewProductDto(AboutReviewProductDto aboutReviewProductDto) {
        this.aboutReviewProductDto = aboutReviewProductDto;
    }

    public ReviewDto() {
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getSoftwareId() {
        return softwareId;
    }

    public void setSoftwareId(int softwareId) {
        this.softwareId = softwareId;
    }

    @Override
    public String toString() {
        return "ReviewDto [reviewId=" + reviewId + ", title=" + title + ", whatYouLike=" + whatYouLike
                + ", whatYouDislike=" + whatYouDislike + ", userRole=" + userRole + ", rating=" + rating + ", userId="
                + userId + ", aboutReviewUserDto=" + aboutReviewUserDto + ", aboutReviewProductDto="
                + aboutReviewProductDto + ", softwareId=" + softwareId + "]";
    }

    public ReviewDto(int reviewId, String title, String whatYouLike, String whatYouDislike, String userRole,
            double rating, int userId, AboutReviewUserDto aboutReviewUserDto,
            AboutReviewProductDto aboutReviewProductDto, int softwareId) {
        this.reviewId = reviewId;
        this.title = title;
        this.whatYouLike = whatYouLike;
        this.whatYouDislike = whatYouDislike;
        this.userRole = userRole;
        this.rating = rating;
        this.userId = userId;
        this.aboutReviewUserDto = aboutReviewUserDto;
        this.aboutReviewProductDto = aboutReviewProductDto;
        this.softwareId = softwareId;
    }

}
