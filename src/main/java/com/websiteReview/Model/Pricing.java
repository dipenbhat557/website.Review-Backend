package com.websiteReview.Model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Pricing {

    @Id
    private int pricingId;
    private List<String> features = new ArrayList<>();
    private double price;

    @ManyToOne
    private Software software;

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

    public Software getSoftware() {
        return software;
    }

    public void setSoftware(Software software) {
        this.software = software;
    }

    public Pricing() {

    }

    public Pricing(int pricingId, List<String> features, double price, Software software) {
        this.pricingId = pricingId;
        this.features = features;
        this.price = price;
        this.software = software;
    }

    @Override
    public String toString() {
        return "Pricing [pricingId=" + pricingId + ", features=" + features + ", price=" + price + ", software="
                + software + "]";
    }

}
