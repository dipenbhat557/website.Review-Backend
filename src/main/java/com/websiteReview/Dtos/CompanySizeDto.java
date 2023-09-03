package com.websiteReview.Dtos;

import java.util.ArrayList;
import java.util.List;

public class CompanySizeDto {

    private int sizeId;

    private String title;

    public int getSizeId() {
        return sizeId;
    }

    public void setSizeId(int sizeId) {
        this.sizeId = sizeId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public CompanySizeDto() {
    }

    public CompanySizeDto(int sizeId, String title) {
        this.sizeId = sizeId;
        this.title = title;
    }

    @Override
    public String toString() {
        return "CompanySizeDto [sizeId=" + sizeId + ", title=" + title + "]";
    }

}
