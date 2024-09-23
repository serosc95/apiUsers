package com.nisumexercise.apiUsers.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
@JsonIgnoreProperties(ignoreUnknown = true)
@Schema(description = "DTO para la información de teléfono")
public class PhoneDto {

    @Schema(description = "Identificador único del teléfono", example = "1")
    private Long id;
    @Schema(description = "Número de teléfono", example = "1234567890")
    @NotBlank(message = "El número de teléfono no puede estar vacío")
    @Pattern(regexp = "^(\\d{7,10})$", message = "El número de teléfono debe tener 10 dígitos")
    private String number;
    @Schema(description = "Código de la ciudad", example = "1")
    @NotBlank(message = "El código de ciudad no puede estar vacío")
    @Pattern(regexp = "^[0-9]+$", message = "El código de ciudad debe contener solo dígitos")
    private String citycode;
    @Schema(description = "Código del país", example = "57")
    @NotBlank(message = "El código de país no puede estar vacío")
    @Pattern(regexp = "^[0-9]+$", message = "El código de país debe contener solo dígitos")
    private String contrycode;

}
