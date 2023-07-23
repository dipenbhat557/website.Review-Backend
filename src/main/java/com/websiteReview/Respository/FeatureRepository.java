package com.websiteReview.Respository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.websiteReview.Model.Features;
import com.websiteReview.Model.Software;

public interface FeatureRepository extends JpaRepository<Features, Integer>{
    
        
    public List<Features> findBySoftware(Software software);
    
}
