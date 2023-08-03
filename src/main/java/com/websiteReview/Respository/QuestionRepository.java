package com.websiteReview.Respository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.websiteReview.Model.Question;

public interface QuestionRepository extends JpaRepository<Question, Integer>{
    
}
