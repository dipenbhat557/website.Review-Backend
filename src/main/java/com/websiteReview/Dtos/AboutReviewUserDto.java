package com.websiteReview.Dtos;

public class AboutReviewUserDto {

    private int reviewUserId;
    private int organizationSize;
    private boolean currentUser;
    private boolean switchFromAnother;
    private String screenshotName;
    private String previousVendor;
    private String reasonOfSwitch;
    private ReviewDto reviewDto;

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
    public ReviewDto getReviewDto() {
        return reviewDto;
    }
    public void setReviewDto(ReviewDto reviewDto) {
        this.reviewDto = reviewDto;
    }
    public AboutReviewUserDto(int reviewUserId, int organizationSize, boolean currentUser, boolean switchFromAnother,
            String screenshotName, String previousVendor, String reasonOfSwitch, ReviewDto reviewDto) {
        this.reviewUserId = reviewUserId;
        this.organizationSize = organizationSize;
        this.currentUser = currentUser;
        this.switchFromAnother = switchFromAnother;
        this.screenshotName = screenshotName;
        this.previousVendor = previousVendor;
        this.reasonOfSwitch = reasonOfSwitch;
        this.reviewDto = reviewDto;
    }
    public AboutReviewUserDto() {
    }
    @Override
    public String toString() {
        return "AboutReviewUserDto [reviewUserId=" + reviewUserId + ", organizationSize=" + organizationSize
                + ", currentUser=" + currentUser + ", switchFromAnother=" + switchFromAnother + ", screenshotName="
                + screenshotName + ", previousVendor=" + previousVendor + ", reasonOfSwitch=" + reasonOfSwitch
                + ", review=" + reviewDto + "]";
    }

    
    
}
