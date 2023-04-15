package com.black.mulberry.core.exception;

/**
 * An exception that indicates that the requested order could not be found.
 */
public class OrderNotFoundException extends RuntimeException {

    /**
     * Constructs a new OrderNotFoundException with the specified detail message.
     *
     * @param message the detail message (which is saved for later retrieval by the getMessage() method)
     */
    public OrderNotFoundException(String message) {
        super(message);
    }
}
