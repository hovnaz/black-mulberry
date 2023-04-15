package com.black.mulberry.core.exception;

/**
 * An exception that indicates that the order could not be confirmed.
 */
public class OrderConfirmedFailException extends RuntimeException {

    /**
     * Constructs a new OrderConfirmedFailException with the specified detail message.
     *
     * @param message the detail message (which is saved for later retrieval by the getMessage() method)
     */
    public OrderConfirmedFailException(String message) {
        super(message);
    }
}
