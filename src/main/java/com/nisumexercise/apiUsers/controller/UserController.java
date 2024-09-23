package com.nisumexercise.apiUsers.controller;

import com.nisumexercise.apiUsers.dto.CreateUserDto;
import com.nisumexercise.apiUsers.dto.UpdateUserDto;
import com.nisumexercise.apiUsers.dto.response.UserResponseDto;
import com.nisumexercise.apiUsers.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<?> createUser(@Valid @RequestBody CreateUserDto userdto) {
        UserResponseDto user = userService.createUser(userdto);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @GetMapping
    public ResponseEntity<?> getAllUsers() {
        List<UserResponseDto> users = userService.findAllUsers();
        return ResponseEntity.status(HttpStatus.OK).body(users);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@Valid @PathVariable UUID id, @RequestBody UpdateUserDto updateUserDto) {
        UserResponseDto updatedUser = userService.updateUser(id, updateUserDto);
        return ResponseEntity.status(HttpStatus.OK).body(updatedUser);
    }
}
