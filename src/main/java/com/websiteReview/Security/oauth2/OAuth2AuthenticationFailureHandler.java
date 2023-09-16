package com.websiteReview.Security.oauth2;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import com.websiteReview.util.CookieUtils;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import static com.websiteReview.Security.oauth2.HttpCookieOAuth2AuthorizationRequestRepository.REDIRECT_URI_PARAM_COOKIE_NAME;

@Component
public class OAuth2AuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

        @Autowired
        HttpCookieOAuth2AuthorizationRequestRepository httpCookieOAuth2AuthorizationRequestRepository;

        @Override
        public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                        AuthenticationException exception) throws IOException, ServletException {
                // Get the target URL from the redirect URI cookie, or use the root URL ("/") by
                // default.
                String targetUrl = CookieUtils.getCookie(request, REDIRECT_URI_PARAM_COOKIE_NAME)
                                .map(Cookie::getValue)
                                .orElse("/");

                // Append the error message as a query parameter to the target URL.
                targetUrl = UriComponentsBuilder.fromUriString(targetUrl)
                                .queryParam("error", exception.getLocalizedMessage())
                                .build()
                                .toUriString();

                // Remove OAuth2 authorization request-related cookies.
                httpCookieOAuth2AuthorizationRequestRepository.removeAuthorizationRequestCookies(request, response);

                // Redirect the user to the target URL with the error message.
                getRedirectStrategy().sendRedirect(request, response, targetUrl);
        }
}
