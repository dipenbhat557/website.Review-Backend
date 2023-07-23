package com.websiteReview.Dtos;

import java.time.Instant;

public class RefreshTokenDto {

    private int tokenId;

    private String refreshToken;

    private Instant expiry;

    private UserDto user;

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

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }

    

    public RefreshTokenDto() {
    }

    

    public RefreshTokenDto(int tokenId, String refreshToken, Instant expiry, UserDto user) {
        this.tokenId = tokenId;
        this.refreshToken = refreshToken;
        this.expiry = expiry;
        this.user = user;
    }

    

    

    public RefreshTokenDto(String refreshToken, Instant expiry, UserDto user) {
        this.refreshToken = refreshToken;
        this.expiry = expiry;
        this.user = user;
    }

    @Override
    public String toString() {
        return "RefreshToken [tokenId=" + tokenId + ", refreshToken=" + refreshToken + ", expiry=" + expiry + ", user="
                + user + "]";
    }

    public int getTokenId() {
        return tokenId;
    }

    public void setTokenId(int tokenId) {
        this.tokenId = tokenId;
    }

    
    
}
