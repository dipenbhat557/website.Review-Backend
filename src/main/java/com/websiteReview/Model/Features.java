package com.websiteReview.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Features {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    public Features(int featureId, String title) {
        this.featureId = featureId;
        this.title = title;
    }

    public Features(){

    }

    @Override
    public String toString() {
        return "Features [featureId=" + featureId + ", title=" + title  + "]";
    }

}
