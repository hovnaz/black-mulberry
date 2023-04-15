package com.black.mulberry.core.facade;

/**
 * The PaymentFacade interface defines methods for processing payments.
 */
public interface PaymentFacade {

    /**
     * Process a payment for the given user ID.
     *
     * @param userId the ID of the user to process the payment for
     */
    void pay(long userId);
}
