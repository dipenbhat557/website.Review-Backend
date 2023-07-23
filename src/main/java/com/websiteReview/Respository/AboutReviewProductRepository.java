package com.websiteReview.Respository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.websiteReview.Model.AboutReviewProduct;
import com.websiteReview.Model.Review;

public interface AboutReviewProductRepository extends JpaRepository<AboutReviewProduct, Integer>{
     
    public AboutReviewProduct findByReview(Review review);

}
