package com.websiteReview.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.websiteReview.Exception.ResourceNotFoundException;
import com.websiteReview.Model.User;
import com.websiteReview.Respository.UserRepository;
import com.websiteReview.Security.UserPrincipal;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    // This method is used to load user details by username (email in this case)
    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        // Find the user by their email in the database or throw an exception if not
        // found
        User user = this.userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("The expected user is not found"));

        // Create a UserPrincipal object from the found user and return it
        return UserPrincipal.create(user);
    }

    // This method is used to load user details by user ID
    @Transactional
    public UserDetails loadUserById(int id) {
        // Find the user by their ID in the database or throw an exception if not found
        User user = this.userRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("The expected user is not found"));

        // Create a UserPrincipal object from the found user and return it
        return UserPrincipal.create(user);
    }
}
