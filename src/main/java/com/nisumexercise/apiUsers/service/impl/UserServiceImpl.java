package com.nisumexercise.apiUsers.service.impl;

import com.nisumexercise.apiUsers.config.security.JwtTokenUtil;
import com.nisumexercise.apiUsers.dto.UserDto;
import com.nisumexercise.apiUsers.dto.response.UserResponseDto;
import com.nisumexercise.apiUsers.entity.User;
import com.nisumexercise.apiUsers.exception.UserAlreadyExistsException;
import com.nisumexercise.apiUsers.repository.UserRepository;
import com.nisumexercise.apiUsers.service.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final JwtTokenUtil jwtTokenUtil;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;

    @Override
    public UserResponseDto createUser(UserDto userDto) {
        if (userRepository.findByEmail(userDto.getEmail()).isPresent()) {
            throw new UserAlreadyExistsException("El correo ya esta registrado");
        }

        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
        User user = modelMapper.map(userDto, User.class);
        user.setDates();

        return mapToResponse(userRepository.save(user));
    }

    @Override
    public List<UserResponseDto> findAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().map(this::mapToResponse).collect(Collectors.toList());
    }

    private UserResponseDto mapToResponse(User user) {
        return UserResponseDto.builder()
                .name(user.getName())
                .email(user.getEmail())
                .id(user.getId())
                .phones(user.getPhones())
                .created(user.getCreated())
                .modified(user.getModified())
                .lastLogin(user.getLastLogin())
                .token(user.getToken())
                .isactive(jwtTokenUtil.validateToken(user.getToken()))
                .build();
    }
}