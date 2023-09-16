package com.websiteReview.Controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.websiteReview.Dtos.RefreshTokenDto;
import com.websiteReview.Exception.BadRequestException;
import com.websiteReview.Exception.ResourceNotFoundException;
import com.websiteReview.Helper.JwtRequest;
import com.websiteReview.Helper.JwtResponse;
import com.websiteReview.Helper.RefreshTokenRequest;
import com.websiteReview.Helper.SignUpRequest;
import com.websiteReview.Model.AuthProvider;
import com.websiteReview.Model.User;
import com.websiteReview.Respository.UserRepository;
import com.websiteReview.Security.JwtHelper;
import com.websiteReview.Security.UserPrincipal;
import com.websiteReview.ServiceImpl.RefreshTokenServiceImpl;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtHelper jwtHelper;

    @Autowired
    private RefreshTokenServiceImpl refreshTokenService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@RequestBody JwtRequest request) {
        // Authenticate the user
        this.authenticateUser(request.getUsername(), request.getPassword());

        // Load user details
        UserDetails userDetails = this.userDetailsService.loadUserByUsername(request.getUsername());

        // Generate JWT token
        String token = this.jwtHelper.generateToken(userDetails);

        // Create a refresh token
        RefreshTokenDto refreshTokenDto = this.refreshTokenService.createRefreshToken(userDetails.getUsername());

        // Create a response containing the JWT token and refresh token
        JwtResponse jwtResponse = new JwtResponse();
        jwtResponse.setToken(token);
        jwtResponse.setRefreshToken(refreshTokenDto.getRefreshToken());

        // Get user details and add them to the response
        User user = this.userRepository.findByEmail(userDetails.getUsername())
                .orElseThrow(() -> new ResourceNotFoundException("The expected user is not found"));
        jwtResponse.setUser(user);

        return new ResponseEntity<JwtResponse>(jwtResponse, HttpStatus.ACCEPTED);
    }

    private void authenticateUser(String username, String password) {
        try {
            // Attempt to authenticate the user
            this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (BadCredentialsException e) {
            throw new ResourceNotFoundException("Invalid username or password");
        }
    }

    @PostMapping("/refresh")
    public ResponseEntity<?> refreshJwtToken(@RequestBody RefreshTokenRequest request) {
        // Verify and retrieve the refresh token
        RefreshTokenDto refreshTokenDto = this.refreshTokenService.verifyRefreshToken(request.getRefreshToken());
        int userId = refreshTokenDto.getUserId();

        // Find the user associated with the refresh token
        User user = this.userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("The expected user is not found"));

        // Generate a new JWT token
        String token = this.jwtHelper.generateToken(UserPrincipal.create(user));

        // Create a response containing the new JWT token and refresh token
        JwtResponse jwtResponse = new JwtResponse(token, refreshTokenDto.getRefreshToken(), user);

        return new ResponseEntity<>(jwtResponse, HttpStatus.OK);
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            throw new BadRequestException("Email address already in use.");
        }

        // Create a new user account
        User user = new User();
        user.setName(signUpRequest.getName());
        user.setEmail(signUpRequest.getEmail());
        user.setPassword(signUpRequest.getPassword());
        user.setProvider(AuthProvider.local);

        // Encode the user's password
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        // Save the user to the database
        User result = userRepository.save(user);

        return new ResponseEntity<>("Registered Successfully", HttpStatus.CREATED);
    }
}
