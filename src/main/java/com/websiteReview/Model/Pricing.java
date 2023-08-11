package com.websiteReview.Model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Pricing {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int pricingId;
    private String title;
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

    public Pricing(int pricingId, String title, List<String> features, double price, Software software) {
        this.pricingId = pricingId;
        this.title = title;
        this.features = features;
        this.price = price;
        this.software = software;
    }

    @Override
    public String toString() {
        return "Pricing [pricingId=" + pricingId + ", title=" + title + ", features=" + features + ", price=" + price
                + ", software=" + software + "]";
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
