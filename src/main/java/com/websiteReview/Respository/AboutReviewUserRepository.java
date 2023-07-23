package com.websiteReview.Respository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.websiteReview.Model.AboutReviewUser;
import com.websiteReview.Model.Review;


public interface AboutReviewUserRepository extends JpaRepository<AboutReviewUser, Integer>{
    
    public AboutReviewUser findByReview(Review review);
}
