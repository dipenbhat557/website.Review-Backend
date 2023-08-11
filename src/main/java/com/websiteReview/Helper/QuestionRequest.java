package com.websiteReview.Helper;

public class QuestionRequest {

    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public QuestionRequest() {
    }

    public QuestionRequest(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "QuestionRequest [description=" + description + "]";
    }

}
