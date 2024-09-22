package com.nisumexercise.apiUsers.exception;

public class RefreshTokenInvalidException extends RuntimeException {

    public RefreshTokenInvalidException(String message) {
        super(message);
    }
}
