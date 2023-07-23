package com.websiteReview.Service;

import java.time.Instant;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.websiteReview.Exception.ResourceNotFoundException;
import com.websiteReview.Helper.RefreshTokenDto;
import com.websiteReview.Helper.UserDto;
import com.websiteReview.Model.RefreshToken;
import com.websiteReview.Model.User;
import com.websiteReview.Respository.RefreshTokenRepository;
import com.websiteReview.Respository.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class RefreshTokenService {

    public long refreshTokenValidity =  60 * 1000;

    @Autowired
    private RefreshTokenRepository refreshTokenRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserRepository userRepository;

    public RefreshTokenDto createRefreshToken(String username) {
        User user = this.userRepository.findByEmail(username).orElseThrow(
                () -> new ResourceNotFoundException("The expected user while generating refresh token is not found"));

        RefreshToken refreshToken = user.getRefreshToken();

        if (refreshToken == null) {
            refreshToken = new RefreshToken(UUID.randomUUID().toString(),
                    Instant.now().plusMillis(refreshTokenValidity), user);
        } else {
            refreshToken.setExpiry(Instant.now().plusMillis(refreshTokenValidity));
        }

        this.refreshTokenRepository.save(refreshToken);

        return this.modelMapper.map(refreshToken, RefreshTokenDto.class);
    }

    public RefreshTokenDto verifyRefreshToken(String providedRefreshToken) {
        RefreshToken refreshToken = this.refreshTokenRepository.findByRefreshToken(providedRefreshToken)
                .orElseThrow(() -> new ResourceNotFoundException("The expected refresh token is not found"));
    
        if (refreshToken.getExpiry().compareTo(Instant.now()) < 0) {
            try {
                this.refreshTokenRepository.delete(refreshToken);
            } catch (Exception e) {
                e.printStackTrace();
            }
            throw new ResourceNotFoundException("The provided refresh token has been expired !!");
        }
    
        return this.modelMapper.map(refreshToken, RefreshTokenDto.class);
    }
    
}

