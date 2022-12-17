package com.black.mulberry.core.repository;

import com.black.mulberry.core.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

    Optional<Payment> findByUserIdAndOrderId(long userId, long orderId);

    Optional<Payment> findByOrderId(long orderId);

    Optional<Payment> findByIdAndUserId(long paymentId, long userId);

}
