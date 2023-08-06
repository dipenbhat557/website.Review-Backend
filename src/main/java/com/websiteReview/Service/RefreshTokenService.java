package com.websiteReview.Service;

import com.websiteReview.Dtos.RefreshTokenDto;

public interface RefreshTokenService {

    public RefreshTokenDto createRefreshToken(String username);

    public RefreshTokenDto verifyRefreshToken(String providedRefreshToken);
}
