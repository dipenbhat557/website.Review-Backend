package com.websiteReview.Helper;

import com.websiteReview.Model.User;

public class JwtResponse {
    
    private String token;
    private User user;
    
    public String getToken() {
        return token;
    }
    public void setToken(String token) {
        this.token = token;
    }
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
    public JwtResponse() {
    }
    public JwtResponse(String token, User user) {
        this.token = token;
        this.user = user;
    }
    @Override
    public String toString() {
        return "JwtResponse [token=" + token + ", user=" + user + "]";
    }

}
