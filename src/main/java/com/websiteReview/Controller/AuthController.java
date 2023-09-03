package com.websiteReview.Controller;

import java.net.URI;

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
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.websiteReview.Dtos.RefreshTokenDto;
import com.websiteReview.Dtos.UserDto;
import com.websiteReview.Exception.BadRequestException;
import com.websiteReview.Exception.ResourceNotFoundException;
import com.websiteReview.Helper.JwtRequest;
import com.websiteReview.Helper.JwtResponse;
import com.websiteReview.Helper.ModelToDto;
import com.websiteReview.Helper.RefreshTokenRequest;
import com.websiteReview.Helper.SignUpRequest;
import com.websiteReview.Model.AuthProvider;
import com.websiteReview.Model.User;
import com.websiteReview.Respository.UserRepository;
import com.websiteReview.Security.JwtHelper;
import com.websiteReview.Security.UserPrincipal;
import com.websiteReview.Service.UserService;
import com.websiteReview.ServiceImpl.RefreshTokenServiceImpl;
import com.websiteReview.ServiceImpl.UserServiceImpl;

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
    private ModelMapper modelMapper;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@RequestBody JwtRequest request) {

        this.authenticateUser(request.getUsername(), request.getPassword());

        UserDetails userDetails = this.userDetailsService.loadUserByUsername(request.getUsername());

        String token = this.jwtHelper.generateToken(userDetails);
        System.out.println(userDetails.getUsername());
        RefreshTokenDto refreshTokenDto = this.refreshTokenService.createRefreshToken(userDetails.getUsername());

        JwtResponse jwtResponse = new JwtResponse();
        jwtResponse.setToken(token);
        jwtResponse.setRefreshToken(refreshTokenDto.getRefreshToken());

        User user = this.userRepository.findByEmail(userDetails.getUsername())
                .orElseThrow(() -> new ResourceNotFoundException("The expected user is not found"));
        jwtResponse.setUser(user);

        return new ResponseEntity<JwtResponse>(jwtResponse, HttpStatus.ACCEPTED);
    }

    private void authenticateUser(String username, String password) {
        try {
            this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (BadCredentialsException e) {
            throw new ResourceNotFoundException("Invalid username or password");
        }
    }

    @PostMapping("/refresh")
    public ResponseEntity<?> refreshJwtToken(@RequestBody RefreshTokenRequest request) {

        RefreshTokenDto refreshTokenDto = this.refreshTokenService.verifyRefreshToken(request.getRefreshToken());
        int userId = refreshTokenDto.getUserId();
        User user = this.userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("The expected user is not found"));
        String token = this.jwtHelper.generateToken(UserPrincipal.create(user));

        JwtResponse jwtResponse = new JwtResponse(token,
                refreshTokenDto.getRefreshToken(),
                user);

        return new ResponseEntity<>(jwtResponse, HttpStatus.OK);

    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            throw new BadRequestException("Email address already in use.");
        }

        // Creating user's account
        User user = new User();
        user.setName(signUpRequest.getName());
        user.setEmail(signUpRequest.getEmail());
        user.setPassword(signUpRequest.getPassword());
        user.setProvider(AuthProvider.local);

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        User result = userRepository.save(user);

        return new ResponseEntity<>("Registered Successfully", HttpStatus.CREATED);
    }

}
