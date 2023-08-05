package com.websiteReview.Respository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.websiteReview.Model.Question;
import com.websiteReview.Model.Software;
import com.websiteReview.Model.User;

public interface QuestionRepository extends JpaRepository<Question, Integer>{

    public Page<Question> findBySoftware(Software software, Pageable pageable);

    public Page<Question> findByUser(User user, Pageable pageable);
    
}
