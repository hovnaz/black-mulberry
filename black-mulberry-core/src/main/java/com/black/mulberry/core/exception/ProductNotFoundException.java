package com.black.mulberry.core.exception;

/**
 * An exception that indicates that the requested product could not be found.
 */
public class ProductNotFoundException extends RuntimeException {

    /**
     * Constructs a new ProductNotFoundException with the specified detail message.
     *
     * @param message the detail message (which is saved for later retrieval by the getMessage() method)
     */
    public ProductNotFoundException(String message) {
        super(message);
    }
}
