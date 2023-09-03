package com.websiteReview.Dtos;

import java.time.Instant;

public class RefreshTokenDto {

    private int tokenId;

    private String refreshToken;

    private Instant expiry;

    private int userId;

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public Instant getExpiry() {
        return expiry;
    }

    public void setExpiry(Instant expiry) {
        this.expiry = expiry;
    }

    public RefreshTokenDto() {
    }

    public int getTokenId() {
        return tokenId;
    }

    public void setTokenId(int tokenId) {
        this.tokenId = tokenId;
    }

    public RefreshTokenDto(int tokenId, String refreshToken, Instant expiry, int userId) {
        this.tokenId = tokenId;
        this.refreshToken = refreshToken;
        this.expiry = expiry;
        this.userId = userId;
    }

    public RefreshTokenDto(String refreshToken, Instant expiry, int userId) {
        this.refreshToken = refreshToken;
        this.expiry = expiry;
        this.userId = userId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "RefreshTokenDto [tokenId=" + tokenId + ", refreshToken=" + refreshToken + ", expiry=" + expiry
                + ", userId=" + userId + "]";
    }

}
