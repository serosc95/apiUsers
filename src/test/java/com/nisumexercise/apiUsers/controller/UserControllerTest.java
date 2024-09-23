package com.nisumexercise.apiUsers.controller;

import com.nisumexercise.apiUsers.dto.CreateUserDto;
import com.nisumexercise.apiUsers.dto.PhoneDto;
import com.nisumexercise.apiUsers.dto.response.UserResponseDto;
import com.nisumexercise.apiUsers.service.impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class UserControllerTest {

    @InjectMocks
    private UserController userController;

    @Mock
    private UserServiceImpl userService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateUserSuccess() {

        List<PhoneDto> phonesDto = new ArrayList<>();
        phonesDto.add(new PhoneDto(1L, "2234567", "1", "57"));
        CreateUserDto createUserDto = new CreateUserDto("test", "test@gmail.com", "test123",
                phonesDto);

        UserResponseDto userResponseDto = new UserResponseDto(UUID.randomUUID(), "test", "test@gmail.com",
                phonesDto, LocalDateTime.now(), LocalDateTime.now(), null, null, false);

        when(userService.createUser(any(CreateUserDto.class))).thenReturn(userResponseDto);

        ResponseEntity<?> response = userController.createUser(createUserDto);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(userResponseDto, response.getBody());
    }


    @Test
    public void testGetAllUsersSuccess() {
        List<PhoneDto> phonesDto = new ArrayList<>();
        phonesDto.add(new PhoneDto(1L, "2234567", "1", "57"));
        UserResponseDto userResponseDto = new UserResponseDto(UUID.randomUUID(), "test", "test@gmail.com",
                phonesDto, LocalDateTime.now(), LocalDateTime.now(), null, null, false);

        when(userService.findAllUsers()).thenReturn(Collections.singletonList(userResponseDto));

        ResponseEntity<?> response = userController.getAllUsers();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(Collections.singletonList(userResponseDto), response.getBody());
    }

    @Test
    public void testGetAllUsersEmpty() {
        when(userService.findAllUsers()).thenReturn(Collections.emptyList());

        ResponseEntity<?> response = userController.getAllUsers();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(Collections.emptyList(), response.getBody());
    }
}
