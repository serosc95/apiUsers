package com.nisumexercise.apiUsers.exception;

public class PasswordIncorrectException extends RuntimeException {

    public PasswordIncorrectException(String message) {
        super(message);
    }
}
