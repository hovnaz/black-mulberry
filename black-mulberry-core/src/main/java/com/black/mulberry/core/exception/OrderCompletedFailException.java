package com.black.mulberry.core.exception;

/**
 * An exception that indicates that the order could not be completed.
 */
public class OrderCompletedFailException extends RuntimeException {

    /**
     * Constructs a new OrderCompletedFailException with the specified detail message.
     *
     * @param message the detail message (which is saved for later retrieval by the getMessage() method)
     */
    public OrderCompletedFailException(String message) {
        super(message);
    }
}
