package com.websiteReview.Helper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.websiteReview.Model.CompanySize;
import com.websiteReview.Model.Pricing;
import com.websiteReview.Model.Review;
import com.websiteReview.Model.SubCategory;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

public class SoftwareRequest {

    private String title;
    private String description;
    private String location;
    private int yearFounded;
    private String language;
    private String differenceFromOthers;
    private String websiteLink;
    private String twitterId;
    private String linkedInId;
    private Map<String, String> features = new HashMap<>();
    private int companySizeId;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getYearFounded() {
        return yearFounded;
    }

    public void setYearFounded(int yearFounded) {
        this.yearFounded = yearFounded;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getDifferenceFromOthers() {
        return differenceFromOthers;
    }

    public void setDifferenceFromOthers(String differenceFromOthers) {
        this.differenceFromOthers = differenceFromOthers;
    }

    public String getWebsiteLink() {
        return websiteLink;
    }

    public void setWebsiteLink(String websiteLink) {
        this.websiteLink = websiteLink;
    }

    public String getTwitterId() {
        return twitterId;
    }

    public void setTwitterId(String twitterId) {
        this.twitterId = twitterId;
    }

    public String getLinkedInId() {
        return linkedInId;
    }

    public void setLinkedInId(String linkedInId) {
        this.linkedInId = linkedInId;
    }

    public Map<String, String> getFeatures() {
        return features;
    }

    public void setFeatures(Map<String, String> features) {
        this.features = features;
    }

    public int getCompanySizeId() {
        return companySizeId;
    }

    public void setCompanySizeId(int companySizeId) {
        this.companySizeId = companySizeId;
    }

    public SoftwareRequest() {
    }

    public SoftwareRequest(String title, String description, String location, int yearFounded, String language,
            String differenceFromOthers, String websiteLink, String twitterId, String linkedInId,
            Map<String, String> features, int companySizeId) {
        this.title = title;
        this.description = description;
        this.location = location;
        this.yearFounded = yearFounded;
        this.language = language;
        this.differenceFromOthers = differenceFromOthers;
        this.websiteLink = websiteLink;
        this.twitterId = twitterId;
        this.linkedInId = linkedInId;
        this.features = features;
        this.companySizeId = companySizeId;
    }

    @Override
    public String toString() {
        return "SoftwareRequest [title=" + title + ", description=" + description + ", location=" + location
                + ", yearFounded=" + yearFounded + ", language=" + language + ", differenceFromOthers="
                + differenceFromOthers + ", websiteLink=" + websiteLink + ", twitterId=" + twitterId + ", linkedInId="
                + linkedInId + ", features=" + features + ", companySizeId=" + companySizeId + "]";
    }

}
