package com.websiteReview.Model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

@Entity
public class Features {

    @Id
    private int featureId;
    private String title;
    private String value;
    
    @ManyToMany
    private List<Software> softwares = new ArrayList<>();

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

    public List<Software> getSoftwares() {
        return softwares;
    }

    public void setSoftwares(List<Software> softwares) {
        this.softwares = softwares;
    }

    public Features(int featureId, String title, String value, List<Software> softwares) {
        this.featureId = featureId;
        this.title = title;
        this.value = value;
        this.softwares = softwares;
    }

    public Features(){

    }

    @Override
    public String toString() {
        return "Features [featureId=" + featureId + ", title=" + title + ", value=" + value + ", softwares=" + softwares
                + "]";
    }

}
