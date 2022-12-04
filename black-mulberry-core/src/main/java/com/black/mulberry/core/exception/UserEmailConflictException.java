package com.black.mulberry.core.exception;

public class UserEmailConflictException extends RuntimeException {

    public UserEmailConflictException(String message) {
        super(message);
    }

    public UserEmailConflictException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserEmailConflictException(Throwable cause) {
        super(cause);
    }

    public UserEmailConflictException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
