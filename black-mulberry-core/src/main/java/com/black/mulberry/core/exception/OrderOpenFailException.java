package com.black.mulberry.core.exception;

/**
 * An exception that indicates that the order could not be opened.
 */
public class OrderOpenFailException extends RuntimeException {

    /**
     * Constructs a new OrderOpenFailException with the specified detail message.
     *
     * @param message the detail message (which is saved for later retrieval by the getMessage() method)
     */
    public OrderOpenFailException(String message) {
        super(message);
    }
}
