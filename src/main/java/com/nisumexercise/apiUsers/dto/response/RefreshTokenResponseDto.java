package com.nisumexercise.apiUsers.dto.response;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class RefreshTokenResponseDto {
    private String accessToken;
}
