package com.websiteReview.Dtos;

import java.util.ArrayList;
import java.util.List;

public class CompanySizeDto {

    private int sizeId;

    private String title;

    private List<SoftwareDto> softwareDtos = new ArrayList<>();

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

    public List<SoftwareDto> getSoftwareDtos() {
        return softwareDtos;
    }

    public void setSoftwareDtos(List<SoftwareDto> softwareDtos) {
        this.softwareDtos = softwareDtos;
    }

    public CompanySizeDto(int sizeId, String title, List<SoftwareDto> softwareDtos) {
        this.sizeId = sizeId;
        this.title = title;
        this.softwareDtos = softwareDtos;
    }

    public CompanySizeDto() {
    }

    @Override
    public String toString() {
        return "CompanySizeDto [sizeId=" + sizeId + ", title=" + title + ", softwares=" + softwareDtos + "]";
    }

}
