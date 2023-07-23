package com.websiteReview.Dtos;

public class FeaturesDto {

    private int featureId;
    private String title;

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

    public FeaturesDto(int featureId, String title) {
        this.featureId = featureId;
        this.title = title;
    }

    public FeaturesDto() {

    }

    @Override
    public String toString() {
        return "Features [featureId=" + featureId + ", title=" + title + "]";
    }

}
