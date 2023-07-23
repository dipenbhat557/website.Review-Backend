package com.websiteReview.Respository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.websiteReview.Model.Review;
import com.websiteReview.Model.User;

public interface ReviewRepository extends JpaRepository<Review, Integer>{
    
    public List<Review> findByUser(User user);
}
