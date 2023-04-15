package com.black.mulberry.core.exception;

/**
 * An exception that indicates that the requested category could not be found.
 */
public class CategoryNotFoundException extends RuntimeException {

    /**
     * Constructs a new CategoryNotFoundException with the specified detail message.
     *
     * @param message the detail message (which is saved for later retrieval by the getMessage() method)
     */
    public CategoryNotFoundException(String message) {
        super(message);
    }
}
