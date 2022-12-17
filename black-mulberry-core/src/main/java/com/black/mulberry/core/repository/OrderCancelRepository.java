package com.black.mulberry.core.repository;

import com.black.mulberry.core.entity.OrderCancel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrderCancelRepository extends JpaRepository<OrderCancel, Long> {

    Optional<OrderCancel> findByOrderId(long orderId);
}
