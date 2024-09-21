package com.nisumexercise.apiUsers.service;

import com.nisumexercise.apiUsers.dto.UserDto;
import com.nisumexercise.apiUsers.entity.User;
import com.nisumexercise.apiUsers.exception.UserAlreadyExistsException;
import com.nisumexercise.apiUsers.repository.UserRepository;
import com.nisumexercise.apiUsers.utils.MethodService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UserService {

    @Autowired
    private final UserRepository userRepository;
    @Autowired
    private final MethodService methodService;
    @Autowired
    private final PasswordEncoder passwordEncoder;

    public User createUser(UserDto userDto){
        if (userRepository.findByEmail(userDto.getEmail()).isPresent()) {
            throw new UserAlreadyExistsException("El correo ya esta registrado");
        }

        User user = new User();
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setCreated(LocalDateTime.now());
        user.setModified(LocalDateTime.now());
        user.setLastLogin(LocalDateTime.now());
        user.setPhones(methodService.mapToPhoneEntity(userDto.getPhones(), user));

        return userRepository.save(user);
    }
}