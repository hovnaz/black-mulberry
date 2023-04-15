package com.black.mulberry.core.exception;

/**
 * An exception that indicates that there is a conflict with the order.
 */
public class OrderConflictException extends RuntimeException {

    /**
     * Constructs a new OrderConflictException with the specified detail message.
     *
     * @param message the detail message (which is saved for later retrieval by the getMessage() method)
     */
    public OrderConflictException(String message) {
        super(message);
    }
}
