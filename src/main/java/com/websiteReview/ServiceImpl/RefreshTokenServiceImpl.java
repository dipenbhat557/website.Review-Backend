package com.websiteReview.ServiceImpl;

import java.time.Instant;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.websiteReview.Dtos.RefreshTokenDto;
import com.websiteReview.Exception.ResourceNotFoundException;
import com.websiteReview.Helper.ModelToDto;
import com.websiteReview.Model.RefreshToken;
import com.websiteReview.Model.User;
import com.websiteReview.Respository.RefreshTokenRepository;
import com.websiteReview.Respository.UserRepository;
import com.websiteReview.Service.RefreshTokenService;

@Service
public class RefreshTokenServiceImpl implements RefreshTokenService {

    public long refreshTokenValidity = 5 * 60 * 60 * 1000;

    @Autowired
    private RefreshTokenRepository refreshTokenRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
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

        return ModelToDto.refreshTokenDto(refreshToken);
    }

    @Override
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

        return ModelToDto.refreshTokenDto(refreshToken);
    }

}
