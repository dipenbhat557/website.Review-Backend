package com.websiteReview.Respository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.websiteReview.Model.Features;

public interface FeatureRepository extends JpaRepository<Features, Integer>{
    
}
