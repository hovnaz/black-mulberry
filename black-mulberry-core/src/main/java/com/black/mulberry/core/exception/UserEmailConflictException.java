package com.black.mulberry.core.exception;

/**
 * An exception that indicates that there is a conflict with the user email.
 */
public class UserEmailConflictException extends RuntimeException {

    /**
     * Constructs a new UserEmailConflictException with the specified detail message.
     *
     * @param message the detail message (which is saved for later retrieval by the getMessage() method)
     */
    public UserEmailConflictException(String message) {
        super(message);
    }
}
