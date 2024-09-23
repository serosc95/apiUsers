package com.nisumexercise.apiUsers.controller;

import com.nisumexercise.apiUsers.dto.CredentialDto;
import com.nisumexercise.apiUsers.dto.RefreshTokenDto;
import com.nisumexercise.apiUsers.dto.response.RefreshTokenResponseDto;
import com.nisumexercise.apiUsers.dto.response.TokenResponseDto;
import com.nisumexercise.apiUsers.exception.RefreshTokenInvalidException;
import com.nisumexercise.apiUsers.exception.UserEmailNotExistsException;
import com.nisumexercise.apiUsers.service.impl.JWTserviceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class AuthControllerTest {

    @InjectMocks
    private AuthController authController;

    @Mock
    private JWTserviceImpl jwtService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testLoginSuccess() {
        CredentialDto credentialDto = new CredentialDto("test@example.com", "test123");
        TokenResponseDto tokenResponseDto = new TokenResponseDto("accessToken", "refreshToken");
        when(jwtService.validateCredentials(any(CredentialDto.class))).thenReturn(tokenResponseDto);

        ResponseEntity<?> response = authController.login(credentialDto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(tokenResponseDto, response.getBody());
    }

    @Test
    public void testLoginInvalidEmail() {
        CredentialDto credentialDto = new CredentialDto("test@example.com", "test123");
        when(jwtService.validateCredentials(any(CredentialDto.class))).thenThrow(
                new UserEmailNotExistsException("El correo: test@example.com no existe"));

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            authController.login(credentialDto);
        });
        assertEquals("El correo: test@example.com no existe", exception.getMessage());
    }

    @Test
    public void testRefreshTokenSuccess() {
        RefreshTokenDto refreshTokenDto = new RefreshTokenDto("refreshToken");
        RefreshTokenResponseDto refreshTokenResponseDto = new RefreshTokenResponseDto("newAccessToken");

        when(jwtService.validateRefreshToken(any(String.class))).thenReturn(refreshTokenResponseDto);

        ResponseEntity<?> response = authController.refreshToken(refreshTokenDto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(refreshTokenResponseDto, response.getBody());
    }

    @Test
    public void testRefreshTokenInvalid() {
        RefreshTokenDto refreshTokenDto = new RefreshTokenDto("invalidRefreshToken");

        when(jwtService.validateRefreshToken(any(String.class))).thenThrow(
                new RefreshTokenInvalidException("El refresh token es invalido"));

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            authController.refreshToken(refreshTokenDto);
        });
        assertEquals("El refresh token es invalido", exception.getMessage());
    }
}
