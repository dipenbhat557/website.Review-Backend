package com.websiteReview.Respository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.websiteReview.Model.Software;

public interface SoftwareRepository extends JpaRepository<Software, Integer>{
    
}
