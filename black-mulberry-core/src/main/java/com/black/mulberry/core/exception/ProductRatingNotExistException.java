package com.black.mulberry.core.exception;

/**
 * An exception that indicates that the product rating does not exist.
 */
public class ProductRatingNotExistException extends RuntimeException {

    /**
     * Constructs a new ProductRatingNotExistException with the specified detail message.
     *
     * @param message the detail message (which is saved for later retrieval by the getMessage() method)
     */
    public ProductRatingNotExistException(String message) {
        super(message);
    }
}
