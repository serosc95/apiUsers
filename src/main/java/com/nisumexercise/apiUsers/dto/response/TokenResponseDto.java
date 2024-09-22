package com.nisumexercise.apiUsers.dto.response;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class TokenResponseDto {
    private String accessToken;
    private String refreshToken;
}
