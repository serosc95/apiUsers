package com.nisumexercise.apiUsers.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "DTO para la solicitud de refresco de token")
public class RefreshTokenDto {
    @Schema(description = "Token de refresh", example = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...")
    @NotBlank(message = "El refreshToken no puede estar vacio")
    @Pattern(regexp = "^[a-zA-Z0-9._-]+$", message = "El formato del refeshToken es invalido")
    private String refreshToken;
}
