package com.nisumexercise.apiUsers.service;

import com.nisumexercise.apiUsers.dto.CreateUserDto;
import com.nisumexercise.apiUsers.dto.UpdateUserDto;
import com.nisumexercise.apiUsers.dto.response.UserResponseDto;

import java.util.List;
import java.util.UUID;

public interface UserService {

    public UserResponseDto createUser(CreateUserDto userDto);

    public List<UserResponseDto> findAllUsers();

    public UserResponseDto updateUser(UUID id, UpdateUserDto updateUserDto);
}
