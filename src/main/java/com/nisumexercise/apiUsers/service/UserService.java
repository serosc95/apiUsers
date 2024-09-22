package com.nisumexercise.apiUsers.service;

import com.nisumexercise.apiUsers.dto.UserDto;
import com.nisumexercise.apiUsers.dto.response.UserResponseDto;

public interface UserService {

    public UserResponseDto createUser(UserDto userDto);
}
