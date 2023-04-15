package com.black.mulberry.core.exception;

/**
 * An exception that indicates that there is a conflict with the payment.
 */
public class PaymentConflictException extends RuntimeException {

    /**
     * Constructs a new PaymentConflictException with the specified detail message.
     *
     * @param message the detail message (which is saved for later retrieval by the getMessage() method)
     */
    public PaymentConflictException(String message) {
        super(message);
    }
}
