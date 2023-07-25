package com.websiteReview.Dtos;

import java.util.ArrayList;
import java.util.List;


public class SubCategoryDto {

    private int subCategoryId;
    private String title;
    private List<CategoryDto> categories = new ArrayList<>();

    public int getSubCategoryId() {
        return subCategoryId;
    }
    public void setSubCategoryId(int subCategoryId) {
        this.subCategoryId = subCategoryId;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public List<CategoryDto> getCategories() {
        return categories;
    }
    public void setCategories(List<CategoryDto> categories) {
        this.categories = categories;
    }
    public SubCategoryDto() {
    }
    public SubCategoryDto(int subCategoryId, String title, List<CategoryDto> categories) {
        this.subCategoryId = subCategoryId;
        this.title = title;
        this.categories = categories;
    }
    @Override
    public String toString() {
        return "SubCategoryDto [subCategoryId=" + subCategoryId + ", title=" + title + ", categories=" + categories
                + "]";
    }

    
    
}
