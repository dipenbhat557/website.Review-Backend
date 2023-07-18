package com.websiteReview.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.websiteReview.Exception.ResourceNotFoundException;
import com.websiteReview.Model.User;
import com.websiteReview.Respository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User createUser(User user){
		user.setPassword(passwordEncoder.encode(user.getPassword()));
        return this.userRepository.save(user);
    }

    public User getUserById(int userId){
        return this.userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("The expected user is not found"));
    }

    public void deleteUser(String email){
        User user = this.userRepository.findByEmail(email).orElseThrow(()->new ResourceNotFoundException("The expected user is not found"));
        this.userRepository.delete(user);
    }
    
    public List<User> getAllUsers(){
        return this.userRepository.findAll();
    }
}
