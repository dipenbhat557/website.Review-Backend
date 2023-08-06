package com.websiteReview.Dtos;

import java.util.ArrayList;
import java.util.List;

public class SubCategoryDto {

    private int subCategoryId;
    private String title;
    private List<CategoryDto> categoryDtos = new ArrayList<>();

    private List<SoftwareDto> softwareDtos = new ArrayList<>();

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

    public List<CategoryDto> getCategoryDtos() {
        return categoryDtos;
    }

    public void setCategoryDtos(List<CategoryDto> categoryDtos) {
        this.categoryDtos = categoryDtos;
    }

    public List<SoftwareDto> getSoftwareDtos() {
        return softwareDtos;
    }

    public void setSoftwareDtos(List<SoftwareDto> softwareDtos) {
        this.softwareDtos = softwareDtos;
    }

    public SubCategoryDto() {
    }

    public SubCategoryDto(int subCategoryId, String title, List<CategoryDto> categoryDtos,
            List<SoftwareDto> softwareDtos) {
        this.subCategoryId = subCategoryId;
        this.title = title;
        this.categoryDtos = categoryDtos;
        this.softwareDtos = softwareDtos;
    }

    @Override
    public String toString() {
        return "SubCategoryDto [subCategoryId=" + subCategoryId + ", title=" + title + ", categories=" + categoryDtos
                + "]";
    }

}
