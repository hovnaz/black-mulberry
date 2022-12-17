package com.black.mulberry.core.exception;

public class UserEmailConflictException extends RuntimeException {

    public UserEmailConflictException(String message) {
        super(message);
    }
}
