package com.websiteReview.Helper;

import java.util.ArrayList;
import java.util.List;

public class CategoryRequest {

    private String title;
    private List<String> purposes = new ArrayList<>();

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public CategoryRequest() {
    }

    public CategoryRequest(String title, List<String> purposes) {
        this.title = title;
        this.purposes = purposes;
    }

    @Override
    public String toString() {
        return "CategoryRequest [title=" + title + ", purposes=" + purposes + "]";
    }

    public List<String> getPurposes() {
        return purposes;
    }

    public void setPurposes(List<String> purposes) {
        this.purposes = purposes;
    }

}
