package com.websiteReview.Dtos;

import java.time.Instant;

public class RefreshTokenDto {

    private int tokenId;

    private String refreshToken;

    private Instant expiry;

    private UserDto userDto;

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

    public UserDto getUserDto() {
        return userDto;
    }

    public void setUserDto(UserDto userDto) {
        this.userDto = userDto;
    }

    public RefreshTokenDto() {
    }

    public RefreshTokenDto(int tokenId, String refreshToken, Instant expiry, UserDto userDto) {
        this.tokenId = tokenId;
        this.refreshToken = refreshToken;
        this.expiry = expiry;
        this.userDto = userDto;
    }

    public RefreshTokenDto(String refreshToken, Instant expiry, UserDto userDto) {
        this.refreshToken = refreshToken;
        this.expiry = expiry;
        this.userDto = userDto;
    }

    @Override
    public String toString() {
        return "RefreshToken [tokenId=" + tokenId + ", refreshToken=" + refreshToken + ", expiry=" + expiry + ", user="
                + userDto + "]";
    }

    public int getTokenId() {
        return tokenId;
    }

    public void setTokenId(int tokenId) {
        this.tokenId = tokenId;
    }

}
