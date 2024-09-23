package com.nisumexercise.apiUsers.dto;

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
public class RefreshTokenDto {
    @NotBlank(message = "El refreshToken no puede estar vacio")
    @Pattern(regexp = "^[a-zA-Z0-9._-]+$", message = "El formato del refeshToken es invalido")
    private String refreshToken;
}
