package com.websiteReview.Respository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.websiteReview.Model.Category;
import com.websiteReview.Model.SubCategory;

public interface SubCategoryRepository extends JpaRepository<SubCategory,Integer>{
    
    public List<SubCategory> findByCategories(Category categories);
}
