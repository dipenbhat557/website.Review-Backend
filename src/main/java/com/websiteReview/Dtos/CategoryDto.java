package com.websiteReview.Dtos;

import java.util.ArrayList;
import java.util.List;

public class CategoryDto {

    private int categoryId;
    private String title;
    
    private List<SubCategoryDto> subCategoryDtos = new ArrayList<>();
    
    public int getCategoryId() {
        return categoryId;
    }
    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }
    public List<SubCategoryDto> getSubCategoryDtos() {
        return subCategoryDtos;
    }
    public void setSubCategoryDtos(List<SubCategoryDto> subCategoryDtos) {
        this.subCategoryDtos = subCategoryDtos;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public CategoryDto() {
    }
    public CategoryDto(int categoryId, String title, List<SubCategoryDto> subCategoryDtos) {
        this.categoryId = categoryId;
        this.title = title;
        this.subCategoryDtos = subCategoryDtos;
    }
    @Override
    public String toString() {
        return "CategoryDto [categoryId=" + categoryId + ", title=" + title + ", subCategoryDtos=" + subCategoryDtos
                + "]";
    }
    
    
}
