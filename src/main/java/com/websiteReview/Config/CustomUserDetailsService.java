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

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        User user = this.userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("The expected user is not found"));
        System.out.println(user);
        return UserPrincipal.create(user);

    }

    @Transactional
    public UserDetails loadUserById(int id) {
        User user = this.userRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("The expected user is not found"));

        return UserPrincipal.create(user);
    }

}
