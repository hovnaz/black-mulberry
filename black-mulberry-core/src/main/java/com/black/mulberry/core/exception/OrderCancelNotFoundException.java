package com.black.mulberry.core.exception;

/**
 * An exception that indicates that the cancellation for the order could not be found.
 */
public class OrderCancelNotFoundException extends RuntimeException {

    /**
     * Constructs a new OrderCancelNotFoundException with the specified detail message.
     *
     * @param message the detail message (which is saved for later retrieval by the getMessage() method)
     */
    public OrderCancelNotFoundException(String message) {
        super(message);
    }
}
