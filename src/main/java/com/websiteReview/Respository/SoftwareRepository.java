package com.websiteReview.Respository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.websiteReview.Model.CompanySize;
import com.websiteReview.Model.Software;
import com.websiteReview.Model.SubCategory;

public interface SoftwareRepository extends JpaRepository<Software, Integer>{

    @Query("SELECT s FROM Software s WHERE s.rating >= :minRating AND s.rating < :maxRating")
    public Page<Software> findByRatingRange(int minRating, int maxRating, Pageable pageable);

    public Page<Software> findBySubCategory(SubCategory subCategory, Pageable pageable);

    public Page<Software> findByCompanySize(CompanySize companySize, Pageable pageable);

    public Page<Software> findByTitleContainingIgnoreCase(String title, Pageable pageable);
    
}
