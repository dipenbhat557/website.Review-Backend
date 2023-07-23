package com.websiteReview.Respository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.websiteReview.Model.RefreshToken;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Integer>{
    
    public Optional<RefreshToken> findByRefreshToken(String refreshToken);
}
