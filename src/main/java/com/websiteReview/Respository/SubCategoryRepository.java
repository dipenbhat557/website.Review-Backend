package com.websiteReview.Respository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.websiteReview.Model.Category;
import com.websiteReview.Model.SubCategory;

public interface SubCategoryRepository extends JpaRepository<SubCategory,Integer>{
    
    public Page<SubCategory> findByCategories(Category categories, Pageable pageable);
}
