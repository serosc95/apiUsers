package com.nisumexercise.apiUsers.controller;

import com.nisumexercise.apiUsers.dto.UserDto;
import com.nisumexercise.apiUsers.entity.User;
import com.nisumexercise.apiUsers.service.UserService;
import com.nisumexercise.apiUsers.utils.MethodService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
@AllArgsConstructor
public class UserController {

    @Autowired
    private final UserService userService;
    @Autowired
    private final MethodService methodService;

    @PostMapping
    public ResponseEntity<?> createUser(@Valid @RequestBody UserDto userdto) {
        User user = userService.createUser(userdto);
        return ResponseEntity.status(HttpStatus.CREATED).body(methodService.mapToResponse(user));
    }
}
