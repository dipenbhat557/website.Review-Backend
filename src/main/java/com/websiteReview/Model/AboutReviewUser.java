package com.websiteReview.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class AboutReviewUser {
    //consists of information about the user giving the review

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int reviewUserId;
    private int organizationSize;
    @Column(name = "`current_user`")
    private boolean currentUser;
    private boolean switchFromAnother;
    private String screenshotName;
    private String previousVendor;
    @Column(length = 5000)
    private String reasonOfSwitch;
    
    @OneToOne
    private Review review;

    public int getReviewUserId() {
        return reviewUserId;
    }

    public void setReviewUserId(int reviewUserId) {
        this.reviewUserId = reviewUserId;
    }

    public int getOrganizationSize() {
        return organizationSize;
    }

    public void setOrganizationSize(int organizationSize) {
        this.organizationSize = organizationSize;
    }

    public boolean isCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(boolean currentUser) {
        this.currentUser = currentUser;
    }

    public boolean isSwitchFromAnother() {
        return switchFromAnother;
    }

    public void setSwitchFromAnother(boolean switchFromAnother) {
        this.switchFromAnother = switchFromAnother;
    }

    public String getScreenshotName() {
        return screenshotName;
    }

    public void setScreenshotName(String screenshotName) {
        this.screenshotName = screenshotName;
    }

    public String getPreviousVendor() {
        return previousVendor;
    }

    public void setPreviousVendor(String previousVendor) {
        this.previousVendor = previousVendor;
    }

    public String getReasonOfSwitch() {
        return reasonOfSwitch;
    }

    public void setReasonOfSwitch(String reasonOfSwitch) {
        this.reasonOfSwitch = reasonOfSwitch;
    }

    public Review getReview() {
        return review;
    }

    public void setReview(Review review) {
        this.review = review;
    }

    public AboutReviewUser() {
    }

    public AboutReviewUser(int reviewUserId, int organizationSize, boolean currentUser, boolean switchFromAnother,
            String screenshotName, String previousVendor, String reasonOfSwitch, Review review) {
        this.reviewUserId = reviewUserId;
        this.organizationSize = organizationSize;
        this.currentUser = currentUser;
        this.switchFromAnother = switchFromAnother;
        this.screenshotName = screenshotName;
        this.previousVendor = previousVendor;
        this.reasonOfSwitch = reasonOfSwitch;
        this.review = review;
    }

    @Override
    public String toString() {
        return "AboutReviewUser [reviewUserId=" + reviewUserId + ", organizationSize=" + organizationSize
                + ", currentUser=" + currentUser + ", switchFromAnother=" + switchFromAnother + ", screenshotName="
                + screenshotName + ", previousVendor=" + previousVendor + ", reasonOfSwitch=" + reasonOfSwitch
                + ", review=" + review + "]";
    }

    
}
