package com.nisumexercise.apiUsers.dto.response;

import com.nisumexercise.apiUsers.dto.PhoneDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "DTO para la respuesta de información de usuario")
public class UserResponseDto {

    @Schema(description = "Identificador único del usuario", example = "123e4567-e89b-12d3-a456-426614174000")
    private UUID id;
    @Schema(description = "Nombre del usuario", example = "test")
    private String name;
    @Schema(description = "Correo electrónico del usuario", example = "test@example.com")
    private String email;
    @Schema(description = "Lista de teléfonos asociados al usuario")
    private List<PhoneDto> phones;
    @Schema(description = "Fecha y hora de creación de la cuenta", example = "2023-06-15T10:30:00")
    private LocalDateTime created;
    @Schema(description = "Fecha y hora de la última modificación de la cuenta", example = "2023-06-16T14:45:00")
    private LocalDateTime modified;
    @Schema(description = "Fecha y hora del último inicio de sesión", example = "2023-06-17T09:15:00")
    private LocalDateTime lastLogin;
    @Schema(description = "Token de acceso del usuario", example = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...")
    private String token;
    @Schema(description = "Indica si eltoken del usuario está activo o no", example = "true")
    private boolean isactive;
}
