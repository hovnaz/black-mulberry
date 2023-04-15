package com.black.mulberry.core.exception;

/**
 * An exception that indicates that the parent of the category could not be found.
 */
public class CategoryParentNotFoundException extends RuntimeException {

    /**
     * Constructs a new CategoryParentNotFoundException with the specified detail message.
     *
     * @param message the detail message (which is saved for later retrieval by the getMessage() method)
     */
    public CategoryParentNotFoundException(String message) {
        super(message);
    }
}
