package com.nisumexercise.apiUsers.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PhoneDto {
    private String number;
    private String citycode;
    private String contrycode;
}
