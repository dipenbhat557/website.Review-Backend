package com.websiteReview.Helper;

public class CompanySizeRequest {

    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public CompanySizeRequest(String title) {
        this.title = title;
    }

    public CompanySizeRequest() {
    }

    @Override
    public String toString() {
        return "CompanySizeRequest [title=" + title + "]";
    }

}
