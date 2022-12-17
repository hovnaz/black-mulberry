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

    public void ifPresentThrow(long userId, long orderId) {
        log.info("find if have payment conflict throws exception");
        Optional<Payment> paymentOptional = paymentRepository.findByUserIdAndOrderId(userId, orderId);
        if (paymentOptional.isPresent()) {
            log.error("payment conflict by order by id: {} and user id: {}", orderId, userId);
            throw new PaymentConflictException("payment conflict by order by id: " + orderId + " and user id: " + userId);
        }
    }

    public boolean isPaidByOrderId(long orderId) {
        Payment payment = paymentService.findByOrderId(orderId);
        return payment.getStatus() == PaymentStatus.PAID;
    }

    public boolean isUnPaid(Payment payment) {
        return payment.getStatus() == PaymentStatus.UN_PAID;
    }
}
