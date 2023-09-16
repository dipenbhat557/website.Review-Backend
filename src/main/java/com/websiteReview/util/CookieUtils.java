package com.websiteReview.util;

import org.springframework.util.SerializationUtils;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Base64;
import java.util.Optional;

public class CookieUtils {

    // Method to get a cookie by name from the request
    public static Optional<Cookie> getCookie(HttpServletRequest request, String name) {
        Cookie[] cookies = request.getCookies();

        if (cookies != null && cookies.length > 0) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(name)) {
                    return Optional.of(cookie); // Found the matching cookie
                }
            }
        }

        return Optional.empty(); // Cookie not found
    }

    // Method to add a new cookie to the response
    public static void addCookie(HttpServletResponse response, String name, String value, int maxAge) {
        Cookie cookie = new Cookie(name, value);
        cookie.setPath("/"); // Cookie is accessible from the root path
        cookie.setHttpOnly(true); // Cookie is not accessible via JavaScript
        cookie.setMaxAge(maxAge); // Maximum age of the cookie in seconds
        response.addCookie(cookie);
    }

    // Method to delete a cookie from the response
    public static void deleteCookie(HttpServletRequest request, HttpServletResponse response, String name) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length > 0) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(name)) {
                    cookie.setValue(""); // Clear the cookie's value
                    cookie.setPath("/"); // Cookie is accessible from the root path
                    cookie.setMaxAge(0); // Set maximum age to 0 (immediately expire)
                    response.addCookie(cookie); // Add the updated cookie to the response
                }
            }
        }
    }

    // Method to serialize an object into a Base64-encoded string
    public static String serialize(Object object) {
        return Base64.getUrlEncoder()
                .encodeToString(SerializationUtils.serialize(object));
    }

    // Method to deserialize an object from a cookie
    public static <T> T deserialize(Cookie cookie, Class<T> cls) {
        return cls.cast(SerializationUtils.deserialize(
                Base64.getUrlDecoder().decode(cookie.getValue())));
    }
}
