package com.nisumexercise.apiUsers.service;

import com.nisumexercise.apiUsers.dto.CredentialDto;
import com.nisumexercise.apiUsers.dto.response.RefreshTokenResponseDto;
import com.nisumexercise.apiUsers.dto.response.TokenResponseDto;

public interface JWTService {

    public TokenResponseDto validateCredentials(CredentialDto credentials);
    public RefreshTokenResponseDto validateRefreshToken(String refreshToken);
}
