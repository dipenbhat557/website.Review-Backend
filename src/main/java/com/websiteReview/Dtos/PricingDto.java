package com.websiteReview.Dtos;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.ManyToOne;

public class PricingDto {

    private int pricingId;
    private List<String> features = new ArrayList<>();
    private double price;

    @ManyToOne
    private SoftwareDto softwareDto;

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

    public SoftwareDto getSoftwareDto() {
        return softwareDto;
    }

    public void setSoftwareDto(SoftwareDto softwareDto) {
        this.softwareDto = softwareDto;
    }

    public PricingDto(int pricingId, List<String> features, double price, SoftwareDto softwareDto) {
        this.pricingId = pricingId;
        this.features = features;
        this.price = price;
        this.softwareDto = softwareDto;
    }

    public PricingDto() {
    }

    @Override
    public String toString() {
        return "PricingDto [pricingId=" + pricingId + ", features=" + features + ", price=" + price + ", softwareDto="
                + softwareDto + "]";
    }

    
    
}
