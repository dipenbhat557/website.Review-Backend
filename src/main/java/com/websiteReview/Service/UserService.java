package com.websiteReview.Service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.websiteReview.Dtos.UserDto;
import com.websiteReview.Exception.ResourceNotFoundException;
import com.websiteReview.Model.User;
import com.websiteReview.Respository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ModelMapper modelMapper;

    public UserDto createUser(UserDto userDto) {
        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));

        User user = this.modelMapper.map(userDto, User.class);
        user = this.userRepository.save(user);
        
        userDto = this.modelMapper.map(user, UserDto.class);

        return userDto;
    }

    public List<UserDto> getAllUsers(){
        List<User> list = this.userRepository.findAll();
        List<UserDto> listDto = list.stream().map( user -> this.modelMapper.map(user,UserDto.class)).collect(Collectors.toList());
        return listDto;
    }

    public UserDto getUserById(int userId) {
        User user = this.userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("The expected user is not found"));
        return this.modelMapper.map(user, UserDto.class);
    }

    public void deleteUser(String email) {
        User user = this.userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("The expected user is not found"));
        this.userRepository.delete(user);
    }

    public UserDto updateUser(UserDto userDto, int userId){
        User oldUser = this.userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("The requested user is not found while updating"));
        //setting new password into oldUser
        oldUser.setPassword(userDto.getPassword());
        
        this.userRepository.save(oldUser);

        // oldUser to oldUserDto
        return this.modelMapper.map(oldUser, UserDto.class);

    }

}
