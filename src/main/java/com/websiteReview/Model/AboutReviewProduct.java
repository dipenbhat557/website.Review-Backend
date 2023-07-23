package com.websiteReview.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class AboutReviewProduct {
    //consists of the information about the product being reviewed

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int aboutProductId;
    private String title;
    private String purposeOfUse;
    private double notionDirectionRating;
    private double easeOfUseRating;
    private double meetsRequirementRating;
    private double qualitySupportRating;

    @OneToOne
    private Review review;

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

    public Review getReview() {
        return review;
    }

    public void setReview(Review review) {
        this.review = review;
    }

    public AboutReviewProduct(int aboutProductId, String title, String purposeOfUse, double notionDirectionRating,
            double easeOfUseRating, double meetsRequirementRating, double qualitySupportRating, Review review) {
        this.aboutProductId = aboutProductId;
        this.title = title;
        this.purposeOfUse = purposeOfUse;
        this.notionDirectionRating = notionDirectionRating;
        this.easeOfUseRating = easeOfUseRating;
        this.meetsRequirementRating = meetsRequirementRating;
        this.qualitySupportRating = qualitySupportRating;
        this.review = review;
    }

    public AboutReviewProduct() {
    }

    @Override
    public String toString() {
        return "AboutReviewProduct [aboutProductId=" + aboutProductId + ", title=" + title + ", purposeOfUse="
                + purposeOfUse + ", notionDirectionRating=" + notionDirectionRating + ", easeOfUseRating="
                + easeOfUseRating + ", meetsRequirementRating=" + meetsRequirementRating + ", qualitySupportRating="
                + qualitySupportRating + ", review=" + review + "]";
    }

    
    
}
