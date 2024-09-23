package com.nisumexercise.apiUsers.service;

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
import com.nisumexercise.apiUsers.service.impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class UserServiceImplTest {

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private JwtTokenUtil jwtTokenUtil;

    @Mock
    private PhoneRepository phoneRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private ModelMapper modelMapper;

    private User user;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        user = new User();
        user.setId(UUID.randomUUID());
        user.setName("Test User");
        user.setEmail("test@example.com");
        user.setCreated(LocalDateTime.now());
        user.setModified(LocalDateTime.now());
        user.setPhones(Collections.singletonList(new Phone()));
    }

    @Test
    void createUserUserDoesNotExistShouldCreateUser() {

        CreateUserDto createUserDto = new CreateUserDto("test", "test@gmail.com", "test123",
                Collections.singletonList(new PhoneDto()));

        when(userRepository.findByEmail(createUserDto.getEmail())).thenReturn(Optional.empty());
        when(passwordEncoder.encode(createUserDto.getPassword())).thenReturn("encodedPassword");
        when(modelMapper.map(createUserDto, User.class)).thenReturn(user);
        when(userRepository.save(any(User.class))).thenReturn(user);
        when(modelMapper.map(user, UserResponseDto.class)).thenReturn(new UserResponseDto());

        UserResponseDto response = userService.createUser(createUserDto);

        assertNotNull(response);
        verify(userRepository).save(any(User.class));
    }

    @Test
    void createUserUserAlreadyExistsShouldThrowException() {
        CreateUserDto createUserDto = new CreateUserDto("test", "test@gmail.com", "test123",
                Collections.singletonList(new PhoneDto()));

        when(userRepository.findByEmail(createUserDto.getEmail())).thenReturn(Optional.of(user));

        assertThrows(UserAlreadyExistsException.class, () -> userService.createUser(createUserDto));
    }

    @Test
    void findAllUsersShouldReturnListOfUsers() {
        when(userRepository.findAll()).thenReturn(List.of(user));
        when(modelMapper.map(user, UserResponseDto.class)).thenReturn(new UserResponseDto());

        List<UserResponseDto> users = userService.findAllUsers();

        assertEquals(1, users.size());
        verify(userRepository).findAll();
    }

    @Test
    void updateUserUserExistsShouldUpdateUser() {
        UpdateUserDto updateUserDto = new UpdateUserDto();
        updateUserDto.setName("Updated User");

        when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));
        when(userRepository.save(any(User.class))).thenReturn(user);
        when(modelMapper.map(user, UserResponseDto.class)).thenReturn(new UserResponseDto());

        UserResponseDto response = userService.updateUser(user.getId(), updateUserDto);

        assertEquals("Updated User", user.getName());
        assertNotNull(response);
        verify(userRepository).save(user);
    }

    @Test
    void updateUserUserDoesNotExistShouldThrowException() {
        UpdateUserDto updateUserDto = new UpdateUserDto();

        when(userRepository.findById(user.getId())).thenReturn(Optional.empty());

        assertThrows(UserEmailNotExistsException.class, () -> userService.updateUser(user.getId(), updateUserDto));
    }
}

