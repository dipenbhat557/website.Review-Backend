package com.websiteReview.Dtos;

import java.util.ArrayList;
import java.util.List;

public class PricingDto {

    private int pricingId;
    private String title;
    private List<String> features = new ArrayList<>();
    private double price;

    private int softwareId;

    public int getPricingId() {
        return pricingId;
    }

    public void setPricingId(int pricingId) {
        this.pricingId = pricingId;
    }

    public List<String> getFeatures() {
        return features;
    }

    public void setFeatures(List<String> features) {
        this.features = features;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public PricingDto() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getSoftwareId() {
        return softwareId;
    }

    public void setSoftwareId(int softwareId) {
        this.softwareId = softwareId;
    }

    public PricingDto(int pricingId, String title, List<String> features, double price, int softwareId) {
        this.pricingId = pricingId;
        this.title = title;
        this.features = features;
        this.price = price;
        this.softwareId = softwareId;
    }

    @Override
    public String toString() {
        return "PricingDto [pricingId=" + pricingId + ", title=" + title + ", features=" + features + ", price=" + price
                + ", softwareId=" + softwareId + "]";
    }

}
