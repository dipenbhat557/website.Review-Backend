package com.websiteReview.Helper;

public class CommentRequest {

    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public CommentRequest() {
    }

    public CommentRequest(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "CommentRequest [description=" + description + "]";
    }

}
