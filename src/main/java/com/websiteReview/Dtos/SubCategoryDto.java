package com.websiteReview.Dtos;

import java.util.ArrayList;
import java.util.List;

import com.websiteReview.Model.Software;


public class SubCategoryDto {

    private int subCategoryId;
    private String title;
    private List<CategoryDto> categories = new ArrayList<>();

    private List<Software> softwares = new ArrayList<>();

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
    
    public SubCategoryDto(int subCategoryId, String title, List<CategoryDto> categories, List<Software> softwares) {
        this.subCategoryId = subCategoryId;
        this.title = title;
        this.categories = categories;
        this.softwares = softwares;
    }
    @Override
    public String toString() {
        return "SubCategoryDto [subCategoryId=" + subCategoryId + ", title=" + title + ", categories=" + categories
                + "]";
    }
    public List<Software> getSoftwares() {
        return softwares;
    }
    public void setSoftwares(List<Software> softwares) {
        this.softwares = softwares;
    }

    
    
}
