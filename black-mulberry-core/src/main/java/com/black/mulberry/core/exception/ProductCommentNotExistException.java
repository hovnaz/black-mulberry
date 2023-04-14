package com.black.mulberry.core.exception;

/**
 * An exception that indicates that the product comment does not exist.
 */
public class ProductCommentNotExistException extends RuntimeException {

    /**
     * Constructs a new ProductCommentNotExistException with the specified detail message.
     *
     * @param message the detail message (which is saved for later retrieval by the getMessage() method)
     */
    public ProductCommentNotExistException(String message) {
        super(message);
    }
}
