package com.black.mulberry.core.support;

import com.black.mulberry.core.entity.Payment;
import com.black.mulberry.core.exception.PaymentConflictException;
import com.black.mulberry.core.repository.PaymentRepository;
import com.black.mulberry.core.service.PaymentService;
import com.black.mulberry.data.transfer.model.PaymentStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * The PaymentSupport class provides methods for checking payment-related information.
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class PaymentSupport {

    private PaymentService paymentService;
    private final PaymentRepository paymentRepository;

    @Autowired
    public void setPaymentService(@Lazy PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    /**
     * Checks if a payment already exists for a given user and order, and throws an exception if it does.
     *
     * @param userId  the ID of the user to check
     * @param orderId the ID of the order to check
     * @throws PaymentConflictException if a payment already exists for the user and order
     */
    public void ifPresentThrow(long userId, long orderId) {
        log.info("Checking for payment conflict");
        Optional<Payment> paymentOptional = paymentRepository.findByUserIdAndOrderId(userId, orderId);
        if (paymentOptional.isPresent()) {
            log.error("Payment conflict for order ID {} and user ID {}", orderId, userId);
            throw new PaymentConflictException("Payment conflict for order ID " + orderId + " and user ID " + userId);
        }
    }

    /**
     * Checks if a payment for a given order has been marked as paid.
     *
     * @param orderId the ID of the order to check
     * @return true if the payment has been marked as paid, false otherwise
     */
    public boolean isPaidByOrderId(long orderId) {
        Payment payment = paymentService.findByOrderId(orderId);
        return payment.getStatus() == PaymentStatus.PAID;
    }

    /**
     * Checks if a payment has not been marked as paid.
     *
     * @param payment the payment to check
     * @return true if the payment has not been marked as paid, false otherwise
     */
    public boolean isUnPaid(Payment payment) {
        return payment.getStatus() == PaymentStatus.UN_PAID;
    }
}
