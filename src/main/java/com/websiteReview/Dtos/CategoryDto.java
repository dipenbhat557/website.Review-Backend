package com.websiteReview.Dtos;

import java.util.ArrayList;
import java.util.List;

public class CategoryDto {

    private int categoryId;
    private String title;
    private List<String> purposes = new ArrayList<>();

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

    public List<String> getPurposes() {
        return purposes;
    }

    public void setPurposes(List<String> purposes) {
        this.purposes = purposes;
    }

    public CategoryDto(int categoryId, String title, List<String> purposes, List<SubCategoryDto> subCategoryDtos) {
        this.categoryId = categoryId;
        this.title = title;
        this.purposes = purposes;
        this.subCategoryDtos = subCategoryDtos;
    }

    @Override
    public String toString() {
        return "CategoryDto [categoryId=" + categoryId + ", title=" + title + ", purposes=" + purposes
                + ", subCategoryDtos=" + subCategoryDtos + "]";
    }

}
