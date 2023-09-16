package com.websiteReview.ServiceImpl;

import java.util.Properties;
import org.springframework.stereotype.Service;

import com.websiteReview.Service.EmailService;

import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailServiceImpl implements EmailService {

    @Override
    public boolean sendEmail(String subject, String message, String to) {
        boolean f = false;

        // Sender's email address(change it to company's)
        String from = "bhattadipen557@gmail.com";

        // SMTP server host
        String host = "smtp.gmail.com";

        // Create properties to configure the SMTP connection
        Properties properties = System.getProperties();

        // Output the properties for debugging
        System.out.println(properties);

        // Configure SMTP properties
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");

        // Step 1: Create a mail session
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                // Provide your Gmail username and password here
                return new PasswordAuthentication("bhattadipen557@gmail.com", "qjvmnmtkvfguzgss");
            }
        });
        session.setDebug(true);

        // Step 2: Compose the email message
        MimeMessage m = new MimeMessage(session);
        try {
            m.setFrom(from);
            m.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
            m.setSubject(subject);
            // Set the email content as HTML
            m.setContent(message, "text/html");

            // Step 3: Sending the message
            Transport.send(m);

            System.out.println("Email sent successfully.");
            f = true;
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        return f;
    }
}
