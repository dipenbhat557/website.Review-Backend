package com.websiteReview.Helper;

public class ReviewRequest {

    private String title;
    private String whatYouLike;
    private String whatYouDislike;
    private String userRole;
    private double rating;

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

    public ReviewRequest() {
    }

    public ReviewRequest(String title, String whatYouLike, String whatYouDislike, String userRole, double rating) {
        this.title = title;
        this.whatYouLike = whatYouLike;
        this.whatYouDislike = whatYouDislike;
        this.userRole = userRole;
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "ReviewRequest [title=" + title + ", whatYouLike=" + whatYouLike + ", whatYouDislike=" + whatYouDislike
                + ", userRole=" + userRole + ", rating=" + rating + "]";
    }

}
