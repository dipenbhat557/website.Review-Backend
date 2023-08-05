package com.websiteReview.Respository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.websiteReview.Model.Comment;
import com.websiteReview.Model.Question;
import com.websiteReview.Model.User;

public interface CommentRepository extends JpaRepository<Comment, Integer>{

    public Page<Comment> findByUser(User user, Pageable pageable);

    public Page<Comment> findByQuestion(Question question, Pageable pageable);
    
}
