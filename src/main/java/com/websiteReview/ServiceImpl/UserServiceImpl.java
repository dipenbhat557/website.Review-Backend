package com.websiteReview.ServiceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.websiteReview.Dtos.UserDto;
import com.websiteReview.Exception.ResourceNotFoundException;
import com.websiteReview.Helper.ModelToDto;
import com.websiteReview.Helper.UserRequest;
import com.websiteReview.Model.AuthProvider;
import com.websiteReview.Model.User;
import com.websiteReview.Respository.UserRepository;
import com.websiteReview.Service.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ModelToDto ModelToDto;

    // Create a new user.
    @Override
    public UserDto create(UserRequest userRequest) {
        User user = new User();
        System.out.println(userRequest);
        user.setName(userRequest.getName());
        user.setEmail(userRequest.getEmail());
        user.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        user.setProvider(AuthProvider.local);
        System.out.println(user);
        user = this.userRepository.save(user);
        System.out.println(user);

        UserDto userDto = ModelToDto.userDto(user);

        return userDto;
    }

    // View all users.
    @Override
    public List<UserDto> viewAll() {
        List<User> users = this.userRepository.findAll();
        List<UserDto> userDtos = users.stream().map(user -> ModelToDto.userDto(user))
                .collect(Collectors.toList());
        return userDtos;
    }

    // View a user by their ID.
    @Override
    public UserDto viewById(int userId) {
        User user = this.userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("The expected user is not found"));
        return ModelToDto.userDto(user);
    }

    // View a user by their email.
    public UserDto viewByEmail(String email) {
        User user = this.userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("The expected user is not found"));
        return ModelToDto.userDto(user);
    }

    // Delete a user by their email.
    @Override
    public void delete(String email) {
        User user = this.userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("The expected user is not found"));
        this.userRepository.delete(user);
    }

    // Update user information by their ID.
    @Override
    public UserDto update(UserDto userDto, int userId) {
        User oldUser = this.userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("The requested user is not found while updating"));

        // Set the new password into the oldUser.
        oldUser.setPassword(userDto.getPassword());

        if (userDto.getImageUrl() != null) {
            oldUser.setImageUrl(userDto.getImageUrl());
        }

        this.userRepository.save(oldUser);

        // Convert oldUser to oldUserDto.
        return ModelToDto.userDto(oldUser);
    }
}
