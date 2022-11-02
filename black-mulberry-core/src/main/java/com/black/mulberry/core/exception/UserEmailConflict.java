package com.black.mulberry.core.exception;

public class UserEmailConflict extends RuntimeException {

    public UserEmailConflict() {
    }

    public UserEmailConflict(String message) {
        super(message);
    }

    public UserEmailConflict(String message, Throwable cause) {
        super(message, cause);
    }

    public UserEmailConflict(Throwable cause) {
        super(cause);
    }

    public UserEmailConflict(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
