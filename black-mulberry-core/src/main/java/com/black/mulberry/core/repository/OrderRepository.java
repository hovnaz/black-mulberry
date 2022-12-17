package com.black.mulberry.core.repository;

import com.black.mulberry.core.entity.Order;
import com.black.mulberry.data.transfer.model.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findByUserIdAndStatusEquals(long userId, OrderStatus status);

    Optional<Order> findByIdAndUserId(long orderId, long userId);

    Optional<Order> findByBasketIdAndUserId(long basketId, long userId);
}
