package com.black.mulberry.core.exception;

/**
 * An exception that indicates that the payment could not be paid.
 */
public class PaymentPaidFailException extends RuntimeException {

    /**
     * Constructs a new PaymentPaidFailException with the specified detail message.
     *
     * @param message the detail message (which is saved for later retrieval by the getMessage() method)
     */
    public PaymentPaidFailException(String message) {
        super(message);
    }
}
