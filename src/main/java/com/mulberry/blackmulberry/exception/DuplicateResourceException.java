package com.mulberry.blackmulberry.exception;

public class DuplicateResourceException extends RuntimeException {

    public DuplicateResourceException() {
    }

    public DuplicateResourceException(String message) {
        super(message);
    }

    public DuplicateResourceException(String message, Throwable cause) {
        super(message, cause);
    }

    public DuplicateResourceException(Throwable cause) {
        super(cause);
    }

    public DuplicateResourceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
