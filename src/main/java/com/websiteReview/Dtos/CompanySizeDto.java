package com.websiteReview.Dtos;

import java.util.ArrayList;
import java.util.List;

import com.websiteReview.Model.Software;

public class CompanySizeDto {

    private int sizeId;

    private String title;

    private List<Software> softwares = new ArrayList<>();

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

    public List<Software> getSoftwares() {
        return softwares;
    }

    public void setSoftwares(List<Software> softwares) {
        this.softwares = softwares;
    }

    public CompanySizeDto(int sizeId, String title, List<Software> softwares) {
        this.sizeId = sizeId;
        this.title = title;
        this.softwares = softwares;
    }

    public CompanySizeDto() {
    }

    @Override
    public String toString() {
        return "CompanySizeDto [sizeId=" + sizeId + ", title=" + title + ", softwares=" + softwares + "]";
    }

    
    
}
