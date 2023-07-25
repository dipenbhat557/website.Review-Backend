package com.websiteReview.Respository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.websiteReview.Model.Category;
import com.websiteReview.Model.SubCategory;

public interface CategoryRepository extends JpaRepository<Category, Integer>{

    public List<Category> findBySubCategories(SubCategory subCategories);
    
}
