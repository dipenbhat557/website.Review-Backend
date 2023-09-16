package com.websiteReview.Security.oauth2.user;

import java.util.Map;

import com.websiteReview.Exception.OAuth2AuthenticationProcessingException;
import com.websiteReview.Model.AuthProvider;

public class OAuth2UserInfoFactory {

    // Factory method to create an instance of OAuth2UserInfo based on the
    // registration ID.
    public static OAuth2UserInfo getOAuth2UserInfo(String registrationId, Map<String, Object> attributes) {
        // Check if the registration ID corresponds to Google OAuth2 provider.
        if (registrationId.equalsIgnoreCase(AuthProvider.google.toString())) {
            return new GoogleOAuth2UserInfo(attributes);
        }
        // Check if the registration ID corresponds to LinkedIn OAuth2 provider.
        else if (registrationId.equalsIgnoreCase(AuthProvider.linkedin.toString())) {
            return new LinkedInOAuth2UserInfo(attributes);
        }
        // If the registration ID doesn't match any supported provider, throw an
        // exception.
        else {
            throw new OAuth2AuthenticationProcessingException(
                    "Sorry! Login with " + registrationId + " is not supported yet.");
        }
    }
}
