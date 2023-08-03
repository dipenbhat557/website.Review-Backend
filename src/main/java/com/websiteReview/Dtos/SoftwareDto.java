package com.websiteReview.Dtos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.websiteReview.Model.CompanySize;

public class SoftwareDto {

    private int softwareId;
    private String title;
    private String description;
    private String location;
    private int yearFounded;
    private String language;
    private String differenceFromOthers;
    private String profileImageName;
    private String websiteLink;
    private String twitterId;
    private String linkedInId;
    private Map<String, String> features = new HashMap<>();
    private String videoName;
    private List<String> screenshots = new ArrayList<>();
    private int noOfResponses;
    private double rating;
    private double notionDirectionRating;
    private double easeOfUseRating;
    private double meetsRequirementRating;
    private double qualitySupportRating;
    private List<ReviewDto> reviewDtos = new ArrayList<>();
    private SubCategoryDto subCategoryDto;
    private List<PricingDto> pricingDtos;
    private CompanySize companySize;

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

    public List<ReviewDto> getReviewDtos() {
        return reviewDtos;
    }

    public void setReviewDtos(List<ReviewDto> reviewDtos) {
        this.reviewDtos = reviewDtos;
    }

    public SubCategoryDto getSubCategoryDto() {
        return subCategoryDto;
    }

    public void setSubCategoryDto(SubCategoryDto subCategoryDto) {
        this.subCategoryDto = subCategoryDto;
    }

    

    public SoftwareDto() {
    }

    

    public SoftwareDto(int softwareId, String title, String description, String location, int yearFounded,
            String language, String differenceFromOthers, String profileImageName, String websiteLink, String twitterId,
            String linkedInId, Map<String, String> features, String videoName, List<String> screenshots,
            int noOfResponses, double rating, double notionDirectionRating, double easeOfUseRating,
            double meetsRequirementRating, double qualitySupportRating, List<ReviewDto> reviewDtos,
            SubCategoryDto subCategoryDto, List<PricingDto> pricingDtos, CompanySize companySize) {
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
        this.reviewDtos = reviewDtos;
        this.subCategoryDto = subCategoryDto;
        this.pricingDtos = pricingDtos;
        this.companySize = companySize;
    }

    @Override
    public String toString() {
        return "SoftwareDto [softwareId=" + softwareId + ", title=" + title + ", description=" + description
                + ", location=" + location + ", yearFounded=" + yearFounded + ", language=" + language
                + ", differenceFromOthers=" + differenceFromOthers + ", profileImageName=" + profileImageName
                + ", websiteLink=" + websiteLink + ", twitterId=" + twitterId + ", linkedInId=" + linkedInId
                + ", features=" + features + ", videoName=" + videoName + ", screenshots=" + screenshots
                + ", noOfResponses=" + noOfResponses + ", rating=" + rating + ", notionDirectionRating="
                + notionDirectionRating + ", easeOfUseRating=" + easeOfUseRating + ", meetsRequirementRating="
                + meetsRequirementRating + ", qualitySupportRating=" + qualitySupportRating + ", reviewDtos="
                + reviewDtos + ", subCategoryDto=" + subCategoryDto + "]";
    }

    public List<PricingDto> getPricingDtos() {
        return pricingDtos;
    }

    public void setPricingDtos(List<PricingDto> pricingDtos) {
        this.pricingDtos = pricingDtos;
    }

    public CompanySize getCompanySize() {
        return companySize;
    }

    public void setCompanySize(CompanySize companySize) {
        this.companySize = companySize;
    }

}
