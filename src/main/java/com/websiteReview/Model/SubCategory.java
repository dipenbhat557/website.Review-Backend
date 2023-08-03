package com.websiteReview.Model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;

@Entity
public class SubCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int subCategoryId;

    private String title;

    @ManyToMany
    private List<Category> categories = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "subCategory")
    @JsonIgnore
    private List<Software> softwares = new ArrayList<>();

    public int getSubCategoryId() {
        return subCategoryId;
    }

    public void setSubCategoryId(int subCategoryId) {
        this.subCategoryId = subCategoryId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public SubCategory(int subCategoryId, String title, List<Category> categories, List<Software> softwares) {
        this.subCategoryId = subCategoryId;
        this.title = title;
        this.categories = categories;
        this.softwares = softwares;
    }

    public SubCategory() {
    }

    @Override
    public String toString() {
        return "SubCategory [subCategoryId=" + subCategoryId + ", title=" + title + ", categories=" + categories + "]";
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public List<Software> getSoftwares() {
        return softwares;
    }

    public void setSoftwares(List<Software> softwares) {
        this.softwares = softwares;
    }

}
