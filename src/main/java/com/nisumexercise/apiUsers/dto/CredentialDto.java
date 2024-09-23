package com.nisumexercise.apiUsers.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "DTO para las credenciales de inicio de sesión")
public class CredentialDto {

    @Schema(description = "Correo electrónico del usuario", example = "test@example.com")
    @NotBlank(message = "El email no puede estar vacío")
    @Email(message = "El correo debe ser válido")
    private String email;
    @Schema(description = "Contraseña del usuario", example = "test123")
    @NotBlank(message = "El password no puede estar vacío")
    private String password;
}