package com.websiteReview.Helper;

public class AboutReviewProductRequest {

    private String title;
    private String purposeOfUse;
    private double notionDirectionRating;
    private double easeOfUseRating;
    private double meetsRequirementRating;
    private double qualitySupportRating;
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
    public AboutReviewProductRequest() {
    }
    public AboutReviewProductRequest(String title, String purposeOfUse, double notionDirectionRating,
            double easeOfUseRating, double meetsRequirementRating, double qualitySupportRating) {
        this.title = title;
        this.purposeOfUse = purposeOfUse;
        this.notionDirectionRating = notionDirectionRating;
        this.easeOfUseRating = easeOfUseRating;
        this.meetsRequirementRating = meetsRequirementRating;
        this.qualitySupportRating = qualitySupportRating;
    }
    @Override
    public String toString() {
        return "AboutReviewProductRequest [title=" + title + ", purposeOfUse=" + purposeOfUse
                + ", notionDirectionRating=" + notionDirectionRating + ", easeOfUseRating=" + easeOfUseRating
                + ", meetsRequirementRating=" + meetsRequirementRating + ", qualitySupportRating="
                + qualitySupportRating + "]";
    }

    
    
}
