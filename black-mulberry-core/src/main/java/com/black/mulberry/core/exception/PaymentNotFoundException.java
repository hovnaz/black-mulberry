package com.black.mulberry.core.exception;

/**
 * An exception that indicates that the requested payment could not be found.
 */
public class PaymentNotFoundException extends RuntimeException {

    /**
     * Constructs a new PaymentNotFoundException with the specified detail message.
     *
     * @param message the detail message (which is saved for later retrieval by the getMessage() method)
     */
    public PaymentNotFoundException(String message) {
        super(message);
    }
}
