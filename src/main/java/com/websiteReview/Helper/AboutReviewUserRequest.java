package com.websiteReview.Helper;

public class AboutReviewUserRequest {

    private int organizationSize;
    private boolean currentUser;
    private boolean switchFromAnother;
    private String previousVendor;
    private String reasonOfSwitch;

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

    public AboutReviewUserRequest() {
    }

    public AboutReviewUserRequest(int organizationSize, boolean currentUser, boolean switchFromAnother,
            String previousVendor, String reasonOfSwitch) {
        this.organizationSize = organizationSize;
        this.currentUser = currentUser;
        this.switchFromAnother = switchFromAnother;
        this.previousVendor = previousVendor;
        this.reasonOfSwitch = reasonOfSwitch;
    }

    @Override
    public String toString() {
        return "AboutReviewUserRequest [organizationSize=" + organizationSize + ", currentUser=" + currentUser
                + ", switchFromAnother=" + switchFromAnother
                + ", previousVendor=" + previousVendor + ", reasonOfSwitch=" + reasonOfSwitch + "]";
    }

}
