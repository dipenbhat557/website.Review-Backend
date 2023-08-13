package com.websiteReview.Service;

public interface EmailService {

    public boolean sendEmail(String subject, String message, String to);

}
