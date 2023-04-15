package com.black.mulberry.core.exception;

/**
 * An exception that indicates that the payment could not be opened.
 */
public class PaymentOpenFailException extends RuntimeException {

    /**
     * Constructs a new PaymentOpenFailException with the specified detail message.
     *
     * @param message the detail message (which is saved for later retrieval by the getMessage() method)
     */
    public PaymentOpenFailException(String message) {
        super(message);
    }
}
