package com.black.mulberry.core.exception;

/**
 * An exception that indicates that the parent of the category is not deleted.
 */
public class CategoryParentIsNotDeletedException extends RuntimeException {

    /**
     * Constructs a new CategoryParentIsNotDeletedException with the specified detail message.
     *
     * @param message the detail message (which is saved for later retrieval by the getMessage() method)
     */
    public CategoryParentIsNotDeletedException(String message) {
        super(message);
    }
}
