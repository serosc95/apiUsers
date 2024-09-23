package com.nisumexercise.apiUsers.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
public class PhoneDto {

    private Long id;
    @NotBlank(message = "El número de teléfono no puede estar vacío")
    @Pattern(regexp = "^(\\d{7,10})$", message = "El número de teléfono debe tener 10 dígitos")
    private String number;
    @NotBlank(message = "El código de ciudad no puede estar vacío")
    @Pattern(regexp = "^[0-9]+$", message = "El código de ciudad debe contener solo dígitos")
    private String citycode;
    @NotBlank(message = "El código de país no puede estar vacío")
    @Pattern(regexp = "^[0-9]+$", message = "El código de país debe contener solo dígitos")
    private String contrycode;

}
