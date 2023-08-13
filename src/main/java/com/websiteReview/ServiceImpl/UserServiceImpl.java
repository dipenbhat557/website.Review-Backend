package com.websiteReview.ServiceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.websiteReview.Dtos.UserDto;
import com.websiteReview.Exception.ResourceNotFoundException;
import com.websiteReview.Helper.DtoToModel;
import com.websiteReview.Helper.ModelToDto;
import com.websiteReview.Helper.UserRequest;
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
    private DtoToModel DtoToModel;

    @Autowired
    private ModelToDto ModelToDto;

    @Override
    public UserDto create(UserRequest userRequest) {
        User user = new User();
        user.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        user = this.userRepository.save(user);

        UserDto userDto = ModelToDto.userDto(user);

        return userDto;
    }

    @Override
    public List<UserDto> viewAll() {
        List<User> users = this.userRepository.findAll();
        List<UserDto> userDtos = users.stream().map(user -> ModelToDto.userDto(user))
                .collect(Collectors.toList());
        return userDtos;
    }

    @Override
    public UserDto viewById(int userId) {
        User user = this.userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("The expected user is not found"));
        return ModelToDto.userDto(user);
    }

    public UserDto viewByEmail(String email){
        User user = this.userRepository.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException("The expected user is not found"));
        return ModelToDto.userDto(user);
    }

    @Override
    public void delete(String email) {
        User user = this.userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("The expected user is not found"));
        this.userRepository.delete(user);
    }

    @Override
    public UserDto update(UserDto userDto, int userId) {
        User oldUser = this.userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("The requested user is not found while updating"));
        // setting new password into oldUser
        oldUser.setPassword(userDto.getPassword());

        this.userRepository.save(oldUser);

        // oldUser to oldUserDto
        return ModelToDto.userDto(oldUser);

    }

}
