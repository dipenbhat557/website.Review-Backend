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

        String from = "bhattadipen557@gmail.com";

        String host = "smtp.gmail.com";

        Properties properties = System.getProperties();

        System.out.println(properties);

        // setting information to properties
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");

        // Step 1:getting session obj
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {

                return new PasswordAuthentication("bhattadipen557@gmail.com", "qjvmnmtkvfguzgss");
            }
        });
        session.setDebug(true);

        // Step 2:Compose msg
        MimeMessage m = new MimeMessage(session);
        try {
            m.setFrom(from);
            m.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
            m.setSubject(subject);
            // m.setText(message);
            m.setContent(message, "text/html");

            // Step 3:Sending message
            Transport.send(m);

            System.out.println("Sent success......");
            f = true;
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        return f;
    }

}
