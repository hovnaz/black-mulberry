package com.black.mulberry.core.exception;

/**
 * An exception that indicates that the user is not authenticated.
 */
public class AuthenticatedException extends RuntimeException {

    /**
     * Constructs a new AuthenticatedException with the specified detail message.
     *
     * @param message the detail message (which is saved for later retrieval by the getMessage() method)
     */
    public AuthenticatedException(String message) {
        super(message);
    }
}
