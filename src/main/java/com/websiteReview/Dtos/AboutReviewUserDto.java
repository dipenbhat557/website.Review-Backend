package com.websiteReview.Dtos;

import com.websiteReview.Model.Review;

public class AboutReviewUserDto {

    private int reviewUserId;
    private int organizationSize;
    private boolean currentUser;
    private boolean switchFromAnother;
    private String screenshotName;
    private String previousVendor;
    private String reasonOfSwitch;
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
    public AboutReviewUserDto(int reviewUserId, int organizationSize, boolean currentUser, boolean switchFromAnother,
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
    public AboutReviewUserDto() {
    }
    @Override
    public String toString() {
        return "AboutReviewUserDto [reviewUserId=" + reviewUserId + ", organizationSize=" + organizationSize
                + ", currentUser=" + currentUser + ", switchFromAnother=" + switchFromAnother + ", screenshotName="
                + screenshotName + ", previousVendor=" + previousVendor + ", reasonOfSwitch=" + reasonOfSwitch
                + ", review=" + review + "]";
    }

    
    
}
