package com.nisumexercise.apiUsers.service;

import com.nisumexercise.apiUsers.dto.UserDto;
import com.nisumexercise.apiUsers.dto.response.UserResponseDTO;
import com.nisumexercise.apiUsers.entity.User;

public interface UserService {

    public UserResponseDTO createUser(UserDto userDto);
}
