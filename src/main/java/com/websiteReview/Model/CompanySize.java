package com.websiteReview.Model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class CompanySize {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int sizeId;

    private String title;

    @OneToMany(mappedBy = "companySize")
    private List<Software> softwares = new ArrayList<>();

    public int getSizeId() {
        return sizeId;
    }

    public void setSizeId(int sizeId) {
        this.sizeId = sizeId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Software> getSoftwares() {
        return softwares;
    }

    public void setSoftwares(List<Software> softwares) {
        this.softwares = softwares;
    }

    public CompanySize(int sizeId, String title, List<Software> softwares) {
        this.sizeId = sizeId;
        this.title = title;
        this.softwares = softwares;
    }

    public CompanySize() {
    }

    @Override
    public String toString() {
        return "CompanySize [sizeId=" + sizeId + ", title=" + title + "]";
    }

}
