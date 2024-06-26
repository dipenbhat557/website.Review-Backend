package com.websiteReview.Controller;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.websiteReview.Dtos.UserDto;
import com.websiteReview.ServiceImpl.EmailServiceImpl;
import com.websiteReview.ServiceImpl.UserServiceImpl;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/forgot-password")
public class ForgotPasswordController {

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private EmailServiceImpl emailService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    // Send OTP to the user's email
    @PostMapping("/send-otp")
    public ResponseEntity<String> sendOTP(@RequestParam("email") String forgotEmail, HttpSession session) {
        // Generate a random OTP
        Random random = new Random(10000);
        int OTP = random.nextInt(100000);

        // Email subject and message
        String subject = "Change Password Request from Website Review System";
        String message = "" +
                "<div style='border:1px solid #e2e2e2;padding:20px'>" +
                "<h1> OTP is <b>" + OTP + "</b></h1>" +
                "</div>";
        String to = forgotEmail;

        // Send the OTP via email
        boolean flag = this.emailService.sendEmail(subject, message, to);

        if (flag) {
            // Store the OTP and email in the session
            session.setAttribute("OTP", OTP);
            session.setAttribute("email", forgotEmail);
            return new ResponseEntity<String>("Successfully sent OTP", HttpStatus.OK);
        } else {
            return new ResponseEntity<String>("Please check your email and correct it", HttpStatus.NOT_FOUND);
        }
    }

    // Verify the OTP provided by the user
    @PostMapping("/verify-otp")
    public ResponseEntity<String> verifyOTP(@RequestParam("otp") int OTP, HttpSession session) {
        int myOtp = (int) session.getAttribute("OTP");

        if (myOtp == OTP) {
            session.removeAttribute("OTP");
            session.setAttribute("OTPVerified", true);
            return new ResponseEntity<String>("Verification complete", HttpStatus.OK);
        } else {
            return new ResponseEntity<String>("You have entered the wrong OTP", HttpStatus.NOT_ACCEPTABLE);
        }
    }

    // Change the user's password after OTP verification
    @PostMapping("/change-password")
    public ResponseEntity<String> changePassword(@RequestParam("newPassword") String newPassword, HttpSession session) {
        String email = (String) session.getAttribute("email");

        if (email == null) {
            return new ResponseEntity<String>("Unauthorized", HttpStatus.UNAUTHORIZED);
        }

        // Check if OTP has been verified
        if (!session.getAttribute("OTPVerified").equals(true)) {
            return new ResponseEntity<String>("OTP verification required", HttpStatus.UNAUTHORIZED);
        }

        // Find the user by email
        UserDto user = this.userService.viewByEmail(email);
        if (user == null) {
            return new ResponseEntity<String>("User not found", HttpStatus.NOT_FOUND);
        }

        // Update the user's password with the new hashed password
        user.setPassword(this.passwordEncoder.encode(newPassword));
        this.userService.update(user, user.getUserId());

        // Clear session attributes
        session.removeAttribute("email");
        session.removeAttribute("OTPVerified");

        return new ResponseEntity<String>("Password changed successfully", HttpStatus.OK);
    }
}
