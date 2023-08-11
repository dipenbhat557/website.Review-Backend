package com.websiteReview.Helper;

import java.util.ArrayList;
import java.util.List;

public class PricingRequest {

    private String title;
    private double price;
    private List<String> features = new ArrayList<>();

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public List<String> getFeatures() {
        return features;
    }

    public void setFeatures(List<String> features) {
        this.features = features;
    }

    public PricingRequest() {
    }

    public PricingRequest(String title, double price, List<String> features) {
        this.title = title;
        this.price = price;
        this.features = features;
    }

    @Override
    public String toString() {
        return "PricingRequest [title=" + title + ", price=" + price + ", features=" + features + "]";
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
