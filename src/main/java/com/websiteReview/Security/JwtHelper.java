package com.websiteReview.Security;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtHelper {

    // JWT token validition time limit(in seconds)
    public static final long JWT_TOKEN_VALIDITY = 2 * 60 * 60;

    // JWT secret key
    private String secret = "dfajklafdkjalafdkljfklqefadsjkfahlkjbadffjkalnfajkldfnasdjkafdrhiofFJKLADFDAJLFADSHJLDHJKLncjdsfklaadfkjafdfjkladsfjkadf";

    // Retrieve username from jwt token
    public String getUsername(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    private <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims allClaimsFromToken = getAllClaimsFromToken(token);
        return claimsResolver.apply(allClaimsFromToken);
    }

    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }

    // Generate a JWT token from user details
    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();

        // Customize claims as needed (e.g., roles, additional information)
        return doGenerateToken(claims, userDetails.getUsername());
    }

    // compaction of the JWT to a url safe string
    private String doGenerateToken(Map<String, Object> claims, String subject) {
        return Jwts.builder().setClaims(claims).setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
                .signWith(SignatureAlgorithm.HS512, secret).compact();
    }

    // Get the expiration date from a JWT token
    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    // check if token has expired
    private boolean isTokenExpired(String token) {
        final Date date = getExpirationDateFromToken(token);
        return date.before(new Date());
    }

    // Validate a JWT token against a user's details
    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = getUsername(token);
        return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }

}
