package com.websiteReview.Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.websiteReview.Helper.MapToJsonConverter;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Software {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int softwareId;
    private String title;
    @Lob
    private String description;
    private String location;
    private int yearFounded;
    private String language;
    @Lob
    private String differenceFromOthers;
    private String profileImageName;
    private String websiteLink;
    private String twitterId;
    private String linkedInId;
    @Convert(converter = MapToJsonConverter.class)
    @Column(columnDefinition = "TEXT")
    private Map<String, String> features = new HashMap<>();
    private String videoName;
    @Lob
    private List<String> screenshots = new ArrayList<>();
    private int noOfResponses;
    private double rating;
    private double notionDirectionRating;
    private double easeOfUseRating;
    private double meetsRequirementRating;
    private double qualitySupportRating;

    @OneToMany(mappedBy = "software", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Review> reviews = new ArrayList<>();

    @ManyToOne
    private SubCategory subCategory;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "software", orphanRemoval = true)
    private List<Pricing> pricings = new ArrayList<>();

    @ManyToOne
    private CompanySize companySize;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "software", orphanRemoval = true)
    private List<Question> questions = new ArrayList<>();

    public int getSoftwareId() {
        return softwareId;
    }

    public void setSoftwareId(int softwareId) {
        this.softwareId = softwareId;
    }

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

    public String getProfileImageName() {
        return profileImageName;
    }

    public void setProfileImageName(String profileImageName) {
        this.profileImageName = profileImageName;
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

    public String getVideoName() {
        return videoName;
    }

    public void setVideoName(String videoName) {
        this.videoName = videoName;
    }

    public List<String> getScreenshots() {
        return screenshots;
    }

    public void setScreenshots(List<String> screenshots) {
        this.screenshots = screenshots;
    }

    public int getNoOfResponses() {
        return noOfResponses;
    }

    public void setNoOfResponses(int noOfResponses) {
        this.noOfResponses = noOfResponses;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public double getNotionDirectionRating() {
        return notionDirectionRating;
    }

    public void setNotionDirectionRating(double notionDirectionRating) {
        this.notionDirectionRating = notionDirectionRating;
    }

    public double getEaseOfUseRating() {
        return easeOfUseRating;
    }

    public void setEaseOfUseRating(double easeOfUseRating) {
        this.easeOfUseRating = easeOfUseRating;
    }

    public double getMeetsRequirementRating() {
        return meetsRequirementRating;
    }

    public void setMeetsRequirementRating(double meetsRequirementRating) {
        this.meetsRequirementRating = meetsRequirementRating;
    }

    public double getQualitySupportRating() {
        return qualitySupportRating;
    }

    public void setQualitySupportRating(double qualitySupportRating) {
        this.qualitySupportRating = qualitySupportRating;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public SubCategory getSubCategory() {
        return subCategory;
    }

    public void setSubCategory(SubCategory subCategory) {
        this.subCategory = subCategory;
    }

    public Software() {
    }

    public Software(int softwareId, String title, String description, String location, int yearFounded, String language,
            String differenceFromOthers, String profileImageName, String websiteLink, String twitterId,
            String linkedInId, Map<String, String> features, String videoName, List<String> screenshots,
            int noOfResponses, double rating, double notionDirectionRating, double easeOfUseRating,
            double meetsRequirementRating, double qualitySupportRating, List<Review> reviews, SubCategory subCategory,
            List<Pricing> pricings, CompanySize companySize, List<Question> questions) {
        this.softwareId = softwareId;
        this.title = title;
        this.description = description;
        this.location = location;
        this.yearFounded = yearFounded;
        this.language = language;
        this.differenceFromOthers = differenceFromOthers;
        this.profileImageName = profileImageName;
        this.websiteLink = websiteLink;
        this.twitterId = twitterId;
        this.linkedInId = linkedInId;
        this.features = features;
        this.videoName = videoName;
        this.screenshots = screenshots;
        this.noOfResponses = noOfResponses;
        this.rating = rating;
        this.notionDirectionRating = notionDirectionRating;
        this.easeOfUseRating = easeOfUseRating;
        this.meetsRequirementRating = meetsRequirementRating;
        this.qualitySupportRating = qualitySupportRating;
        this.reviews = reviews;
        this.subCategory = subCategory;
        this.pricings = pricings;
        this.companySize = companySize;
        this.questions = questions;
    }

    @Override
    public String toString() {
        return "Software [softwareId=" + softwareId + ", title=" + title + ", description=" + description
                + ", location=" + location + ", yearFounded=" + yearFounded + ", language=" + language
                + ", differenceFromOthers=" + differenceFromOthers + ", profileImageName=" + profileImageName
                + ", websiteLink=" + websiteLink + ", twitterId=" + twitterId + ", linkedInId=" + linkedInId
                + ", features=" + features + ", videoName=" + videoName + ", screenshots=" + screenshots
                + ", noOfResponses=" + noOfResponses + ", rating=" + rating + ", notionDirectionRating="
                + notionDirectionRating + ", easeOfUseRating=" + easeOfUseRating + ", meetsRequirementRating="
                + meetsRequirementRating + ", qualitySupportRating=" + qualitySupportRating
                + ", subCategory=" + subCategory  + ", companySize=" + companySize
                + "]";
    }

    public List<Pricing> getPricings() {
        return pricings;
    }

    public void setPricings(List<Pricing> pricings) {
        this.pricings = pricings;
    }

    public CompanySize getCompanySize() {
        return companySize;
    }

    public void setCompanySize(CompanySize companySize) {
        this.companySize = companySize;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

}
