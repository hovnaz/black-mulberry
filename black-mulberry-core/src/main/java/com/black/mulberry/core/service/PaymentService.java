package com.black.mulberry.core.service;

import com.black.mulberry.core.entity.Payment;

public interface PaymentService {

    /**
     * open payment from order id
     *
     * @param userId  by id
     * @param orderId by id
     * @return opened payment
     * @see com.black.mulberry.data.transfer.model.PaymentStatus
     */
    Payment open(long userId, long orderId);

    /**
     * pay
     *
     * @param payment
     * @param userId
     * @see com.black.mulberry.data.transfer.model.PaymentStatus
     */
    void pay(Payment payment, long userId);

    Payment findByOrderId(long orderId);

    Payment findByIdAndUserId(long paymentId, long userId);
}
