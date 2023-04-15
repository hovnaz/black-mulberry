package com.black.mulberry.core.exception;

/**
 * An exception that indicates that the order could not be cancelled.
 */
public class OrderCancelFailException extends RuntimeException {

    /**
     * Constructs a new OrderCancelFailException with the specified detail message.
     *
     * @param message the detail message (which is saved for later retrieval by the getMessage() method)
     */
    public OrderCancelFailException(String message) {
        super(message);
    }
}
