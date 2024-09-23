package com.nisumexercise.apiUsers.dto;

import lombok.Data;

@Data
public class UpdateUserDto {

    private String name;
    private String number;
    private String citycode;
    private String contrycode;
}
