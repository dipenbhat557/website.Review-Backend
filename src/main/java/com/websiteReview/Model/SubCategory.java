package com.websiteReview.Model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

@Entity
public class SubCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int subCategoryId;
    
    private String title;

    @ManyToMany
    private List<Category> categories = new ArrayList<>();

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

    

    public SubCategory(int subCategoryId, String title, List<Category> categories) {
        this.subCategoryId = subCategoryId;
        this.title = title;
        this.categories = categories;
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

    
    
}
