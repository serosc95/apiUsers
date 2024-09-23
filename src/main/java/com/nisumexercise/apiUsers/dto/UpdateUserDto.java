package com.nisumexercise.apiUsers.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class UpdateUserDto {

    @Schema(description = "Nuevo nombre del usuario", example = "test")
    private String name;
    @Schema(description = "Nuevo número de teléfono", example = "123456789")
    private String number;
    @Schema(description = "Nuevo código de ciudad", example = "1")
    private String citycode;
    @Schema(description = "Nuevo código de país", example = "57")
    private String contrycode;
}
