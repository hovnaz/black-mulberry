package com.black.mulberry.core.exception;

/**
 * An exception that indicates that the category contains products and cannot be deleted.
 */
public class CategoryProductIsNotEmptyException extends RuntimeException {

    /**
     * Constructs a new CategoryProductIsNotEmptyException with the specified detail message.
     *
     * @param message the detail message (which is saved for later retrieval by the getMessage() method)
     */
    public CategoryProductIsNotEmptyException(String message) {
        super(message);
    }
}
