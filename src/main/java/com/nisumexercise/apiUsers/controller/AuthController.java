package com.nisumexercise.apiUsers.controller;

import com.nisumexercise.apiUsers.dto.CredentialDto;
import com.nisumexercise.apiUsers.dto.RefreshTokenDto;
import com.nisumexercise.apiUsers.dto.response.RefreshTokenResponseDto;
import com.nisumexercise.apiUsers.dto.response.TokenResponseDto;
import com.nisumexercise.apiUsers.service.impl.JWTserviceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AuthController {

    private final JWTserviceImpl jwtService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody CredentialDto credentials) {

        TokenResponseDto tokens = jwtService.validateCredentials(credentials);
        return ResponseEntity.status(HttpStatus.OK).body(tokens);
    }

    @PostMapping("/refresh")
    public ResponseEntity<?> refreshToken(@Valid @RequestBody RefreshTokenDto request) {

        RefreshTokenResponseDto accessToken = jwtService.validateRefreshToken(request.getRefreshToken());
        return ResponseEntity.status(HttpStatus.OK).body(accessToken);
    }
}
