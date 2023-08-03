package com.websiteReview.Respository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.websiteReview.Model.CompanySize;

public interface CompanySizeRepository extends JpaRepository<CompanySize, Integer>{
    
}
