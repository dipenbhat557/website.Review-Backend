package com.websiteReview.Service;

import java.util.List;

import com.websiteReview.Dtos.UserDto;

public interface UserService {

    public UserDto create(UserDto userDto);

    public List<UserDto> viewAll();

    public UserDto viewById(int userId);

    public void delete(String email);

    public UserDto update(UserDto userDto, int userId);

}
