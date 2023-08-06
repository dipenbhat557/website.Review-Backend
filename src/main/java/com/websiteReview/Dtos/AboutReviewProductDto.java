package com.websiteReview.Dtos;

public class AboutReviewProductDto {
    
    private int aboutProductId;
    private String title;
    private String purposeOfUse;
    private double notionDirectionRating;
    private double easeOfUseRating;
    private double meetsRequirementRating;
    private double qualitySupportRating;
    private ReviewDto reviewDto;

    public int getAboutProductId() {
        return aboutProductId;
    }
    public void setAboutProductId(int aboutProductId) {
        this.aboutProductId = aboutProductId;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getPurposeOfUse() {
        return purposeOfUse;
    }
    public void setPurposeOfUse(String purposeOfUse) {
        this.purposeOfUse = purposeOfUse;
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
    public ReviewDto getReviewDto() {
        return reviewDto;
    }
    public void setReviewDto(ReviewDto reviewDto) {
        this.reviewDto = reviewDto;
    }
    public AboutReviewProductDto() {
    }
    public AboutReviewProductDto(int aboutProductId, String title, String purposeOfUse, double notionDirectionRating,
            double easeOfUseRating, double meetsRequirementRating, double qualitySupportRating, ReviewDto reviewDto) {
        this.aboutProductId = aboutProductId;
        this.title = title;
        this.purposeOfUse = purposeOfUse;
        this.notionDirectionRating = notionDirectionRating;
        this.easeOfUseRating = easeOfUseRating;
        this.meetsRequirementRating = meetsRequirementRating;
        this.qualitySupportRating = qualitySupportRating;
        this.reviewDto = reviewDto;
    }
    @Override
    public String toString() {
        return "AboutReviewProductDto [aboutProductId=" + aboutProductId + ", title=" + title + ", purposeOfUse="
                + purposeOfUse + ", notionDirectionRating=" + notionDirectionRating + ", easeOfUseRating="
                + easeOfUseRating + ", meetsRequirementRating=" + meetsRequirementRating + ", qualitySupportRating="
                + qualitySupportRating + ", review=" + reviewDto + "]";
    }

    
}
