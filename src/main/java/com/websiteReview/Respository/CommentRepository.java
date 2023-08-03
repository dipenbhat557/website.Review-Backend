package com.websiteReview.Respository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.websiteReview.Model.Comment;

public interface CommentRepository extends JpaRepository<Comment, Integer>{
    
}
