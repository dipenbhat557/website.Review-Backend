package com.websiteReview.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

@Entity
public class Review {
    //consists of the core information about the particular review

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int reviewId;
    private int title;
    @Column(length = 2500)
    private String whatYouLike;
    @Column(length = 2500)
    private String whatYouDislike;
    private String userRole;
    private double rating;

    @ManyToOne
    private User user;

    @OneToOne(mappedBy = "review", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private AboutReviewUser aboutReviewUser;

    @OneToOne(mappedBy = "review", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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

    public Review() {
    }

    public Review(int reviewId, int title, String whatYouLike, String whatYouDislike, String userRole, double rating,
            User user, AboutReviewUser aboutReviewUser, AboutReviewProduct aboutReviewProduct) {
        this.reviewId = reviewId;
        this.title = title;
        this.whatYouLike = whatYouLike;
        this.whatYouDislike = whatYouDislike;
        this.userRole = userRole;
        this.rating = rating;
        this.user = user;
        this.aboutReviewUser = aboutReviewUser;
        this.aboutReviewProduct = aboutReviewProduct;
    }

    @Override
    public String toString() {
        return "Review [reviewId=" + reviewId + ", title=" + title + ", whatYouLike=" + whatYouLike
                + ", whatYouDislike=" + whatYouDislike + ", userRole=" + userRole + ", rating=" + rating
                + "]";
    }

    
}
