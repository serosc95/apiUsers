package com.nisumexercise.apiUsers.exception;

public class UserEmailNotExistsException extends RuntimeException {

    public UserEmailNotExistsException(String message) {
        super(message);
    }
}
