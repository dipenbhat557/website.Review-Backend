package com.websiteReview.Service;

import java.util.List;

import com.websiteReview.Dtos.UserDto;
import com.websiteReview.Helper.UserRequest;

public interface UserService {

    public UserDto create(UserRequest userRequest);

    public List<UserDto> viewAll();

    public UserDto viewById(int userId);

    public void delete(String email);

    public UserDto update(UserDto userDto, int userId);

}
