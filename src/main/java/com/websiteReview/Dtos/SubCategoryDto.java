package com.websiteReview.Dtos;

public class SubCategoryDto {

    private int subCategoryId;
    private String title;

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

    public SubCategoryDto() {
    }

    public SubCategoryDto(int subCategoryId, String title) {
        this.subCategoryId = subCategoryId;
        this.title = title;
    }

    @Override
    public String toString() {
        return "SubCategoryDto [subCategoryId=" + subCategoryId + ", title=" + title + "]";
    }

}
