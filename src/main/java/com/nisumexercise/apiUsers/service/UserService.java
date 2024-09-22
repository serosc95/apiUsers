package com.nisumexercise.apiUsers.service;

import com.nisumexercise.apiUsers.dto.UserDto;
import com.nisumexercise.apiUsers.dto.response.UserResponseDto;

import java.util.List;

public interface UserService {

    public UserResponseDto createUser(UserDto userDto);

    public List<UserResponseDto> findAllUsers();
}
