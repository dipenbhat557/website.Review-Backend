package com.websiteReview.Dtos;

import java.util.ArrayList;
import java.util.List;

import com.websiteReview.Model.AuthProvider;
import com.websiteReview.Model.RefreshToken;

public class UserDto {
    private int userId;

    private String name;
    private String email;

    private String imageUrl;

    private Boolean emailVerified = false;

    private String password;

    private AuthProvider provider;

    private String providerId;

    private RefreshTokenDto refreshTokenDto;

    private List<ReviewDto> reviewDtos = new ArrayList<>();

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Boolean getEmailVerified() {
        return emailVerified;
    }

    public void setEmailVerified(Boolean emailVerified) {
        this.emailVerified = emailVerified;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public AuthProvider getProvider() {
        return provider;
    }

    public void setProvider(AuthProvider provider) {
        this.provider = provider;
    }

    public String getProviderId() {
        return providerId;
    }

    public void setProviderId(String providerId) {
        this.providerId = providerId;
    }

    public RefreshTokenDto getRefreshTokenDto() {
        return refreshTokenDto;
    }

    public void setRefreshTokenDto(RefreshTokenDto refreshTokenDto) {
        this.refreshTokenDto = refreshTokenDto;
    }

    public List<ReviewDto> getReviewDtos() {
        return reviewDtos;
    }

    public void setReviewDtos(List<ReviewDto> reviewDtos) {
        this.reviewDtos = reviewDtos;
    }

    public UserDto() {
    }

    public UserDto(int userId, String name, String email, String imageUrl, Boolean emailVerified, String password,
            AuthProvider provider, String providerId, RefreshTokenDto refreshTokenDto, List<ReviewDto> reviewDtos) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.imageUrl = imageUrl;
        this.emailVerified = emailVerified;
        this.password = password;
        this.provider = provider;
        this.providerId = providerId;
        this.refreshTokenDto = refreshTokenDto;
        this.reviewDtos = reviewDtos;
    }

    @Override
    public String toString() {
        return "UserDto [userId=" + userId + ", name=" + name + ", email=" + email + ", imageUrl=" + imageUrl
                + ", emailVerified=" + emailVerified + ", provider=" + provider
                + ", providerId=" + providerId + ", refreshTokenDto=" + refreshTokenDto + ", reviewDtos=" + reviewDtos
                + "]";
    }

}
