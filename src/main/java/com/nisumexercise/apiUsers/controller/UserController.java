package com.nisumexercise.apiUsers.controller;

import com.nisumexercise.apiUsers.dto.UserDto;
import com.nisumexercise.apiUsers.entity.User;
import com.nisumexercise.apiUsers.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.management.openmbean.KeyAlreadyExistsException;
import java.util.Collections;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/user")
@AllArgsConstructor
public class UserController {

    @Autowired
    private final UserService userService;

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody UserDto userdto) {
        try {
            User user = userService.createUser(userdto);
            return ResponseEntity.status(HttpStatus.CREATED).body(user);
        } catch (KeyAlreadyExistsException e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(Collections.singletonMap("mensaje", e.getMessage()));
        }
    }
}
