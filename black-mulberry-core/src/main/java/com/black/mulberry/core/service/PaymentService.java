package com.black.mulberry.core.service;

import com.black.mulberry.core.entity.Payment;

/**
 * The PaymentService interface provides methods for managing payments related to orders.
 */
public interface PaymentService {

    /**
     * Opens a payment for a given order and user.
     *
     * @param userId the ID of the user associated with the payment
     * @param orderId the ID of the order associated with the payment
     * @return the opened Payment object
     * @see com.black.mulberry.data.transfer.model.PaymentStatus
     */
    Payment open(long userId, long orderId);

    /**
     * Processes a payment for a given Payment object and user.
     *
     * @param payment the Payment object to be processed
     * @param userId the ID of the user associated with the payment
     * @see com.black.mulberry.data.transfer.model.PaymentStatus
     */
    void pay(Payment payment, long userId);

    /**
     * Finds a Payment object by its associated order ID.
     *
     * @param orderId the ID of the order associated with the payment
     * @return the Payment object associated with the order ID
     */
    Payment findByOrderId(long orderId);

    /**
     * Finds a Payment object by its ID and the ID of the associated user.
     *
     * @param paymentId the ID of the Payment object
     * @param userId the ID of the user associated with the payment
     * @return the Payment object with the given ID and associated user ID
     */
    Payment findByIdAndUserId(long paymentId, long userId);
}
