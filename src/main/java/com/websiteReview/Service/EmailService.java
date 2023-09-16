package com.websiteReview.Service;

public interface EmailService {

    /**
     * Sends an email with the specified subject and message to the given recipient.
     *
     * @param subject The subject of the email.
     * @param message The content of the email.
     * @param to      The email address of the recipient.
     * @return `true` if the email was sent successfully, `false` otherwise.
     */
    public boolean sendEmail(String subject, String message, String to);
}
