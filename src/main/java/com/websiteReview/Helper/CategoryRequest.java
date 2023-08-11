package com.websiteReview.Helper;

public class CategoryRequest {

    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public CategoryRequest() {
    }

    public CategoryRequest(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "CategoryRequest [title=" + title + "]";
    }

}
