package com.nisumexercise.apiUsers.service.impl;

import com.nisumexercise.apiUsers.config.security.JwtTokenUtil;
import com.nisumexercise.apiUsers.dto.CreateUserDto;
import com.nisumexercise.apiUsers.dto.PhoneDto;
import com.nisumexercise.apiUsers.dto.UpdateUserDto;
import com.nisumexercise.apiUsers.dto.response.UserResponseDto;
import com.nisumexercise.apiUsers.entity.Phone;
import com.nisumexercise.apiUsers.entity.User;
import com.nisumexercise.apiUsers.exception.UserAlreadyExistsException;
import com.nisumexercise.apiUsers.exception.UserEmailNotExistsException;
import com.nisumexercise.apiUsers.repository.PhoneRepository;
import com.nisumexercise.apiUsers.repository.UserRepository;
import com.nisumexercise.apiUsers.service.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final JwtTokenUtil jwtTokenUtil;
    private final UserRepository userRepository;
    private final PhoneRepository phoneRepository;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;

    @Override
    public UserResponseDto createUser(CreateUserDto userDto) {
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

    @Override
    public UserResponseDto updateUser(UUID id, UpdateUserDto updateUserDto) {
        Optional<User> userOpt = userRepository.findById(id);
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            if (updateUserDto.getName() != null) {
                user.setName(updateUserDto.getName());
            }

            Phone phone = user.getPhones().stream().findFirst().orElse(new Phone());
            boolean phoneUpdated = false;
            if (updateUserDto.getNumber() != null) {
                phone.setNumber(updateUserDto.getNumber());
                phoneUpdated = true;
            }
            if (updateUserDto.getCitycode() != null) {
                phone.setCitycode(updateUserDto.getCitycode());
                phoneUpdated = true;
            }
            if (updateUserDto.getContrycode() != null) {
                phone.setContrycode(updateUserDto.getContrycode());
                phoneUpdated = true;
            }

            if (phoneUpdated) {
                if (phone.getId() == null) {
                    phone.setUser(user);
                    user.getPhones().add(phone);
                } else {
                    phoneRepository.save(phone);
                }
            }

            user.setModified(LocalDateTime.now());
            return mapToResponse(userRepository.save(user));
        }
        throw new UserEmailNotExistsException(String.format("El usuario con id: %s no existe", id));
    }

    private UserResponseDto mapToResponse(User user) {
        List<PhoneDto> phoneDto = Optional.ofNullable(user.getPhones())
                .orElse(Collections.emptyList())
                .stream()
                .map(phone -> modelMapper.map(phone, PhoneDto.class))
                .collect(Collectors.toList());

        return UserResponseDto.builder()
                .name(user.getName())
                .email(user.getEmail())
                .id(user.getId())
                .phones(phoneDto)
                .created(user.getCreated())
                .modified(user.getModified())
                .lastLogin(user.getLastLogin())
                .token(user.getToken())
                .isactive(jwtTokenUtil.validateToken(user.getToken()))
                .build();
    }
}