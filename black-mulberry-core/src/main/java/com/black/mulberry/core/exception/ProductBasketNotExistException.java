package com.black.mulberry.core.exception;

/**
 * An exception that indicates that the product basket does not exist.
 */
public class ProductBasketNotExistException extends RuntimeException {

    /**
     * Constructs a new ProductBasketNotExistException with the specified detail message.
     *
     * @param message the detail message (which is saved for later retrieval by the getMessage() method)
     */
    public ProductBasketNotExistException(String message) {
        super(message);
    }
}
