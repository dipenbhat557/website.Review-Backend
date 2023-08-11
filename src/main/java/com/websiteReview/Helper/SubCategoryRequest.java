package com.websiteReview.Helper;

import java.util.List;

import com.websiteReview.Model.Category;

public class SubCategoryRequest {

    private String title;
    private List<Category> categories;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public SubCategoryRequest(String title, List<Category> categories) {
        this.title = title;
        this.categories = categories;
    }

    public SubCategoryRequest() {
    }

    @Override
    public String toString() {
        return "SubCategoryRequest [title=" + title + ", categories=" + categories + "]";
    }

}
