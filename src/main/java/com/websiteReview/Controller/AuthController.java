package com.websiteReview.Controller;

import java.util.Map;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.websiteReview.Dtos.RefreshTokenDto;
import com.websiteReview.Dtos.UserDto;
import com.websiteReview.Exception.ResourceNotFoundException;
import com.websiteReview.Helper.JwtRequest;
import com.websiteReview.Helper.JwtResponse;
import com.websiteReview.Helper.RefreshTokenRequest;
import com.websiteReview.Model.User;
import com.websiteReview.Security.JwtHelper;
import com.websiteReview.Service.RefreshTokenService;

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
    private RefreshTokenService refreshTokenService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@RequestBody JwtRequest request) {

        this.authenticateUser(request.getUsername(), request.getPassword());

        UserDetails userDetails = this.userDetailsService.loadUserByUsername(request.getUsername());

        String token = this.jwtHelper.generateToken(userDetails);
        RefreshTokenDto refreshTokenDto = this.refreshTokenService.createRefreshToken(userDetails.getUsername());

        JwtResponse jwtResponse = new JwtResponse();
        jwtResponse.setToken(token);
        jwtResponse.setRefreshToken(refreshTokenDto.getRefreshToken());
        jwtResponse.setUser((User) userDetails);

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
        UserDto userDto = refreshTokenDto.getUser();
        String token = this.jwtHelper.generateToken(this.modelMapper.map(userDto, User.class));

        JwtResponse jwtResponse = new JwtResponse(token, refreshTokenDto.getRefreshToken(),
                this.modelMapper.map(userDto, User.class));

        return new ResponseEntity<>(jwtResponse, HttpStatus.OK);

    }

}
