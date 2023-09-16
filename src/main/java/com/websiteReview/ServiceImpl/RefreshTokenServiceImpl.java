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

    // Define the validity period of the refresh token (in milliseconds)
    public long refreshTokenValidity = 5 * 60 * 60 * 1000; // 5 hours

    @Autowired
    private RefreshTokenRepository refreshTokenRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelToDto ModelToDto;

    @Override
    public RefreshTokenDto createRefreshToken(String username) {
        // Find the user by their email (username)
        User user = this.userRepository.findByEmail(username).orElseThrow(
                () -> new ResourceNotFoundException("The expected user while generating refresh token is not found"));

        // Get the user's existing refresh token, if it exists
        RefreshToken refreshToken = user.getRefreshToken();

        if (refreshToken == null) {
            // If no existing refresh token, create a new one with a unique ID and set its
            // expiry time
            refreshToken = new RefreshToken(UUID.randomUUID().toString(),
                    Instant.now().plusMillis(refreshTokenValidity), user);
        } else {
            // If an existing refresh token is found, update its expiry time
            refreshToken.setExpiry(Instant.now().plusMillis(refreshTokenValidity));
        }

        // Save the refresh token to the database
        this.refreshTokenRepository.save(refreshToken);

        // Convert the refresh token entity to a DTO and return it
        return ModelToDto.refreshTokenDto(refreshToken);
    }

    @Override
    public RefreshTokenDto verifyRefreshToken(String providedRefreshToken) {
        // Find the refresh token by the provided token value
        RefreshToken refreshToken = this.refreshTokenRepository.findByRefreshToken(providedRefreshToken)
                .orElseThrow(() -> new ResourceNotFoundException("The expected refresh token is not found"));

        // Check if the refresh token has expired
        if (refreshToken.getExpiry().compareTo(Instant.now()) < 0) {
            try {
                // If expired, delete the token from the database
                this.refreshTokenRepository.delete(refreshToken);
            } catch (Exception e) {
                e.printStackTrace();
            }
            throw new ResourceNotFoundException("The provided refresh token has expired.");
        }

        // Convert the refresh token entity to a DTO and return it
        return ModelToDto.refreshTokenDto(refreshToken);
    }
}
