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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.websiteReview.Exception.ResourceNotFoundException;
import com.websiteReview.Helper.JwtRequest;
import com.websiteReview.Helper.JwtResponse;
import com.websiteReview.Helper.RefreshTokenDto;
import com.websiteReview.Helper.RefreshTokenRequest;
import com.websiteReview.Helper.UserDto;
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

    @Autowired
    private OAuth2AuthorizedClientService authclientService;

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

    @RequestMapping("/oauth2LoginSuccess")
    public ResponseEntity<?> getOauth2LoginInfo(@AuthenticationPrincipal OAuth2AuthenticationToken authenticationToken) {
        // fetching the client details and user details
        System.out.println(authenticationToken.getAuthorizedClientRegistrationId()); // client name like facebook,
                                                                                     // google etc.
        System.out.println(authenticationToken.getName()); // facebook/google userId

        // 1.Fetching User Info
        OAuth2User user = authenticationToken.getPrincipal(); // When you login with OAuth it gives you OAuth2User else
                                                              // UserDetails
        System.out.println("userId: " + user.getName()); // returns the userId of facebook something like 12312312313212
        // getAttributes map Contains User details like name, email etc// print the
        // whole map for more details
        System.out.println("Email:" + user.getAttributes().get("email"));

        // 2. Just in case if you want to obtain User's auth token value, refresh token,
        // expiry date etc you can use below snippet
        OAuth2AuthorizedClient client = authclientService.loadAuthorizedClient(
                authenticationToken.getAuthorizedClientRegistrationId(),
                authenticationToken.getName());
        System.out.println("Token Value" + client.getAccessToken().getTokenValue());

        // 3. Now you have full control on users data.You can eitehr see if user is not
        // present in Database then store it and
        // send welcome email for the first time
        return new ResponseEntity<>(Map.of("name",user.getAttributes().get("name")),HttpStatus.OK);
    }

}
