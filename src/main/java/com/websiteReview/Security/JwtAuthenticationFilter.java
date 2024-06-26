package com.websiteReview.Security;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.MalformedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    Logger logger = LoggerFactory.getLogger(JwtAuthenticationEntryPoint.class);

    @Autowired
    private JwtHelper jwtHelper;

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        // Get the JWT token from the request header
        String requestToken = request.getHeader("Authorization");
        logger.info("The request token is {}", requestToken);

        String username = null;
        String jwtToken = null;

        if (requestToken != null && requestToken.startsWith("Bearer ")) {
            // Extract the JWT token (remove "Bearer " prefix)
            jwtToken = requestToken.substring(7);

            try {
                // Get the username from the JWT token
                username = this.jwtHelper.getUsername(jwtToken);

            } catch (MalformedJwtException e) {
                logger.info("Invalid token message", "Invalid Jwt Token");
            } catch (IllegalArgumentException e) {
                logger.info("Invalid token message", "Unable to get token");
            }

            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

                // Validate the JWT token
                UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);

                if (this.jwtHelper.validateToken(jwtToken, userDetails)) {

                    // Create an authentication object and set it in the SecurityContext
                    UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(userDetails,
                            null, userDetails.getAuthorities());

                    auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(auth);

                } else {
                    logger.info("Could not validate token {}", "Invalid Jwt Token");
                }
            } else {
                logger.info("User message", "Username is null or auth is already there");
            }
        }
        // Continue processing the request
        filterChain.doFilter(request, response);
    }
}
