package com.websiteReview.Service;

import com.websiteReview.Dtos.RefreshTokenDto;

public interface RefreshTokenService {

    /**
     * Creates a new refresh token for the given username.
     *
     * @param username The username for which to create the refresh token.
     * @return The created RefreshTokenDto.
     */
    public RefreshTokenDto createRefreshToken(String username);

    /**
     * Verifies the provided refresh token.
     *
     * @param providedRefreshToken The refresh token to be verified.
     * @return The RefreshTokenDto if the provided token is valid, otherwise null.
     */
    public RefreshTokenDto verifyRefreshToken(String providedRefreshToken);
}
