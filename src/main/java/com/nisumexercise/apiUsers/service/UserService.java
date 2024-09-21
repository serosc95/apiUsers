package com.nisumexercise.apiUsers.service;

import com.nisumexercise.apiUsers.dto.UserDto;
import com.nisumexercise.apiUsers.entity.User;
import com.nisumexercise.apiUsers.repository.UserRepository;
import com.nisumexercise.apiUsers.utils.MethodService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.management.openmbean.KeyAlreadyExistsException;
import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class UserService {

    @Autowired
    private final UserRepository userRepository;
    @Autowired
    private final MethodService methodService;

    public User createUser(UserDto userDto) {
        if (userRepository.findByEmail(userDto.getEmail()).isPresent()) {
            throw new KeyAlreadyExistsException("El correo ya registrado");
        }

        User user = new User();
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setPhones(methodService.mapToPhoneEntity(userDto.getPhones()));
        user.setCreated(LocalDateTime.now());
        user.setModified(LocalDateTime.now());

        return userRepository.save(user);
    }
}