package com.nisumexercise.apiUsers.controller;

import com.nisumexercise.apiUsers.dto.CredentialDto;
import com.nisumexercise.apiUsers.dto.RefreshTokenDto;
import com.nisumexercise.apiUsers.dto.response.RefreshTokenResponseDto;
import com.nisumexercise.apiUsers.dto.response.TokenResponseDto;
import com.nisumexercise.apiUsers.service.impl.JWTserviceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Autenticación", description = "API de autenticación y manejo de tokens")
public class AuthController {

    private final JWTserviceImpl jwtService;

    @PostMapping("/login")
    @Operation(summary = "Iniciar sesión", description = "Valida las credenciales del usuario y " +
            "devuelve tokens de acceso y refresco")
    @ApiResponse(responseCode = "200", description = "Inicio de sesión exitoso",
            content = @Content(schema = @Schema(implementation = TokenResponseDto.class)))
    @ApiResponse(responseCode = "401", description = "Credenciales inválidas")
    public ResponseEntity<?> login(@Valid @RequestBody CredentialDto credentials) {

        TokenResponseDto tokens = jwtService.validateCredentials(credentials);
        return ResponseEntity.status(HttpStatus.OK).body(tokens);
    }

    @PostMapping("/refresh")
    @Operation(summary = "Refresh token", description = "Genera un nuevo token de " +
            "acceso usando un refreshtoken válido")
    @ApiResponse(responseCode = "200", description = "Token creeado exitosamente",
            content = @Content(schema = @Schema(implementation = RefreshTokenResponseDto.class)))
    @ApiResponse(responseCode = "401", description = "refeshtoken inválido")
    public ResponseEntity<?> refreshToken(@Valid @RequestBody RefreshTokenDto request) {

        RefreshTokenResponseDto accessToken = jwtService.validateRefreshToken(request.getRefreshToken());
        return ResponseEntity.status(HttpStatus.OK).body(accessToken);
    }
}
