package com.websiteReview.Helper;

public class FilterByPurposeRequest {

    private String purpose;

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public FilterByPurposeRequest() {
    }

    public FilterByPurposeRequest(String purpose) {
        this.purpose = purpose;
    }

    @Override
    public String toString() {
        return "FilterByPurposeRequest [purpose=" + purpose + "]";
    }

    
}
