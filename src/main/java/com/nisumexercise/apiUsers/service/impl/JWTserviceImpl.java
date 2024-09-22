package com.nisumexercise.apiUsers.service.impl;

import com.nisumexercise.apiUsers.config.security.JwtTokenUtil;
import com.nisumexercise.apiUsers.dto.CredentialDto;
import com.nisumexercise.apiUsers.dto.response.RefreshTokenResponseDto;
import com.nisumexercise.apiUsers.dto.response.TokenResponseDto;
import com.nisumexercise.apiUsers.entity.User;
import com.nisumexercise.apiUsers.exception.PasswordIncorrectException;
import com.nisumexercise.apiUsers.exception.RefreshTokenInvalidException;
import com.nisumexercise.apiUsers.exception.UserEmailNotExistsException;
import com.nisumexercise.apiUsers.repository.UserRepository;
import com.nisumexercise.apiUsers.service.JWTService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class JWTserviceImpl implements JWTService {

    @Autowired
    private final JwtTokenUtil jwtTokenUtil;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @Override
    public TokenResponseDto validateCredentials(CredentialDto credentials) {
        Optional<User> userOptional = userRepository.findByEmail(credentials.getEmail());
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            boolean isValid = passwordEncoder.matches(credentials.getPassword(), user.getPassword());
            if (isValid) {
                return mapToResponseTokens(user);
            }
            throw new PasswordIncorrectException("La contraseña es incorrecta");
        }
        throw new UserEmailNotExistsException(String.format("El correo: %s no existe", credentials.getEmail()));
    }

    @Override
    public RefreshTokenResponseDto validateRefreshToken(String refreshToken) {
        if (jwtTokenUtil.validateToken(refreshToken)) {
            String email = jwtTokenUtil.getEmailFromToken(refreshToken);
            return mapToResponseRefreshToken(email);
        } else {
            throw new RefreshTokenInvalidException("El refresh token es invalido");
        }
    }

    private TokenResponseDto mapToResponseTokens(User user) {
        return TokenResponseDto.builder()
                .accessToken(jwtTokenUtil.generateAccessToken(user.getEmail()))
                .refreshToken(jwtTokenUtil.generateRefreshToken(user.getEmail()))
                .build();
    }

    private RefreshTokenResponseDto mapToResponseRefreshToken(String email) {
        return RefreshTokenResponseDto.builder()
                .accessToken(jwtTokenUtil.generateAccessToken(email))
                .build();
    }
}
