package com.nisumexercise.apiUsers.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.util.List;

@Data
public class CreateUserDto {

    @NotBlank(message = "El nombre no puede estar vacío")
    private String name;
    @NotBlank(message = "El correo no puede estar vacío")
    @Email(message = "El correo debe ser válido")
    private String email;
    @NotBlank(message = "La contraseña no puede estar vacía")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[A-Za-z\\d]{8,}$", message = "La contraseña no cumple los requisitos dados")
    private String password;
    private List<@Valid PhoneDto> phones;
}
