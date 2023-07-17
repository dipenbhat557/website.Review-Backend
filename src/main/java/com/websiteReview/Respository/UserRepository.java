package com.websiteReview.Respository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.websiteReview.Model.User;

public interface UserRepository extends JpaRepository<User,Integer>{

}
