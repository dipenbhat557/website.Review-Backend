package com.websiteReview.Dtos;

import java.util.ArrayList;
import java.util.List;

import com.websiteReview.Model.Software;

public class FeaturesDto {

    private int featureId;
    private String title;
    private String value;
    private List<SoftwareDto> softwareDtos = new ArrayList<>();

    public int getFeatureId() {
        return featureId;
    }

    public void setFeatureId(int featureId) {
        this.featureId = featureId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public List<SoftwareDto> getSoftwareDtos() {
        return softwareDtos;
    }

    public void setSoftwareDtos(List<SoftwareDto> softwareDtos) {
        this.softwareDtos = softwareDtos;
    }

    public FeaturesDto(int featureId, String title, String value, List<SoftwareDto> softwareDtos) {
        this.featureId = featureId;
        this.title = title;
        this.value = value;
        this.softwareDtos = softwareDtos;
    }

    public FeaturesDto() {

    }

    @Override
    public String toString() {
        return "Features [featureId=" + featureId + ", title=" + title + ", value=" + value + ", softwares="
                + softwareDtos
                + "]";
    }

}
