package com.websiteReview.Respository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.websiteReview.Model.Pricing;
import com.websiteReview.Model.Software;

public interface PricingRepository extends JpaRepository<Pricing,Integer>{
    
    public List<Pricing> findBySoftware(Software software);
}
