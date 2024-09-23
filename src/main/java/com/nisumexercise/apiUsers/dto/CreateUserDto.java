package com.nisumexercise.apiUsers.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import io.swagger.v3.oas.annotations.media.Schema;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "DTO para crear un nuevo usuario")
public class CreateUserDto {

    @Schema(description = "Nombre del usuario", example = "Test")
    @NotBlank(message = "El nombre no puede estar vacío")
    private String name;
    @Schema(description = "Correo electrónico del usuario", example = "test@example.com")
    @NotBlank(message = "El correo no puede estar vacío")
    @Email(message = "El correo debe ser válido")
    private String email;
    @Schema(description = "Contraseña del usuario", example = "Password123")
    @NotBlank(message = "La contraseña no puede estar vacía")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[A-Za-z\\d]{8,}$", message = "La contraseña no cumple los requisitos dados")
    private String password;
    private List<@Valid PhoneDto> phones;
}
