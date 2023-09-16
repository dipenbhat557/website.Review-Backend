package com.websiteReview.Security.oauth2;

import static com.websiteReview.Security.oauth2.HttpCookieOAuth2AuthorizationRequestRepository.REDIRECT_URI_PARAM_COOKIE_NAME;

import java.io.IOException;
import java.net.URI;
import java.util.Optional;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import com.websiteReview.Config.AppProperties;
import com.websiteReview.Exception.BadRequestException;
import com.websiteReview.Security.JwtHelper;
import com.websiteReview.Security.UserPrincipal;
import com.websiteReview.util.CookieUtils;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class OAuth2AuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private JwtHelper jwtHelper; // Helper class for JWT operations

    private AppProperties appProperties; // Application properties

    private HttpCookieOAuth2AuthorizationRequestRepository httpCookieOAuth2AuthorizationRequestRepository;

    // Constructor to inject dependencies
    public OAuth2AuthenticationSuccessHandler(JwtHelper jwtHelper, AppProperties appProperties,
            HttpCookieOAuth2AuthorizationRequestRepository httpCookieOAuth2AuthorizationRequestRepository) {
        this.jwtHelper = jwtHelper;
        this.appProperties = appProperties;
        this.httpCookieOAuth2AuthorizationRequestRepository = httpCookieOAuth2AuthorizationRequestRepository;
    }

    // Override method to handle successful authentication
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {
        // Determine the target URL based on the request, response, and authentication
        String targetUrl = determineTargetUrl(request, response, authentication);

        if (response.isCommitted()) {
            // If the response is already committed, log a message and return
            logger.debug("Response has already been committed. Unable to redirect to " + targetUrl);
            return;
        }

        // Clear any authentication attributes
        clearAuthenticationAttributes(request, response);

        // Redirect the user to the determined target URL
        getRedirectStrategy().sendRedirect(request, response, targetUrl);
    }

    // Determine the target URL based on the request, response, and authentication
    protected String determineTargetUrl(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) {
        // Retrieve the redirect URI from the cookie, if available
        Optional<String> redirectUri = CookieUtils.getCookie(request, REDIRECT_URI_PARAM_COOKIE_NAME)
                .map(Cookie::getValue);

        if (redirectUri.isPresent() && !isAuthorizedRedirectUri(redirectUri.get())) {
            // Check if the redirect URI is authorized; otherwise, throw a
            // BadRequestException
            throw new BadRequestException(
                    "Sorry! We've got an Unauthorized Redirect URI and can't proceed with the authentication");
        }

        // Obtain the target URL from the redirect URI cookie or use the default target
        // URL
        String targetUrl = redirectUri.orElse(getDefaultTargetUrl());

        // Generate a JWT token for the authenticated user
        String token = jwtHelper.generateToken((UserPrincipal) authentication.getPrincipal());

        // Append the JWT token as a query parameter to the target URL
        return UriComponentsBuilder.fromUriString(targetUrl)
                .queryParam("token", token)
                .build()
                .toUriString();
    }

    // Clear any authentication attributes and cookies
    protected void clearAuthenticationAttributes(HttpServletRequest request, HttpServletResponse response) {
        super.clearAuthenticationAttributes(request);
        httpCookieOAuth2AuthorizationRequestRepository.removeAuthorizationRequestCookies(request, response);
    }

    // Check if the redirect URI is authorized
    private boolean isAuthorizedRedirectUri(String uri) {
        URI clientRedirectUri = URI.create(uri);

        return appProperties.getOauth2().getAuthorizedRedirectUris()
                .stream()
                .anyMatch(authorizedRedirectUri -> {
                    // Only validate host and port; let the clients use different paths if they want
                    // to
                    URI authorizedURI = URI.create(authorizedRedirectUri);
                    if (authorizedURI.getHost().equalsIgnoreCase(clientRedirectUri.getHost())
                            && authorizedURI.getPort() == clientRedirectUri.getPort()) {
                        return true;
                    }
                    return false;
                });
    }
}
