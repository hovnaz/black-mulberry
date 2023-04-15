package com.black.mulberry.core.exception;

/**
 * An exception that indicates that there are repeated users.
 */
public class RepeatUsersException extends RuntimeException {

    /**
     * Constructs a new RepeatUsersException with the specified detail message.
     *
     * @param message the detail message (which is saved for later retrieval by the getMessage() method)
     */
    public RepeatUsersException(String message) {
        super(message);
    }
}
