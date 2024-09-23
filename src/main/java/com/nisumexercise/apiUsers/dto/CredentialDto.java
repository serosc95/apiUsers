package com.nisumexercise.apiUsers.dto;

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
public class CredentialDto {

    @NotBlank(message = "El email no puede estar vacío")
    @Email(message = "El correo debe ser válido")
    private String email;
    @NotBlank(message = "El password no puede estar vacío")
    private String password;
}