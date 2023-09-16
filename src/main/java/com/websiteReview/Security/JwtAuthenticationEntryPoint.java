package com.websiteReview.Security;

import java.io.IOException;
import java.io.PrintWriter;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
            AuthenticationException authException) throws IOException, ServletException {
        // Set the HTTP response status to 401 (Unauthorized) when authentication fails
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

        // Get a PrintWriter to write a custom error message to the response
        PrintWriter writer = response.getWriter();

        // Write a custom error message to the response
        writer.println("Access Denied !! " + authException);
    }
}
