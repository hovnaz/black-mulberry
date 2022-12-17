package com.black.mulberry.core.service.impl;

import com.black.mulberry.core.entity.Order;
import com.black.mulberry.core.entity.OrderCancel;
import com.black.mulberry.core.exception.OrderCancelFailException;
import com.black.mulberry.core.exception.OrderCancelNotFoundException;
import com.black.mulberry.core.repository.OrderCancelRepository;
import com.black.mulberry.core.repository.OrderRepository;
import com.black.mulberry.core.service.OrderCancelService;
import com.black.mulberry.core.service.OrderService;
import com.black.mulberry.data.transfer.model.OrderStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderCancelServiceImpl implements OrderCancelService {

    private final OrderCancelRepository orderCancelRepository;
    private final OrderRepository orderRepository;
    private final OrderService orderService;

    @Override
    public void cancel(long orderId, String description) {
        log.info("Request cancel order by id: {}", orderId);
        Order order = orderService.findById(orderId);
        Optional<OrderCancel> orderCancelOptional = orderCancelRepository.findByOrderId(orderId);
        if (order.getStatus() == OrderStatus.CANCELED || orderCancelOptional.isPresent()) {
            log.error("order already canceled id: {}", orderId);
            throw new OrderCancelFailException("order already canceled id: " + orderId);
        } else if (order.getStatus() == OrderStatus.COMPLETED) {
            log.error("order in status completed cannot be cancelled id: {}", orderId);
            throw new OrderCancelFailException("order in status completed cannot be cancelled id: " + orderId);
        }
        OrderCancel orderCancel = OrderCancel.builder()
                .isApproved(false)
                .order(order)
                .description(description)
                .build();

        orderCancelRepository.save(orderCancel);
        log.info("successes add request cancel order id: {}", orderId);
    }

    @Override
    public void approveCancelled(long orderId) {
        log.info("request approve cancel order by id: {}", orderId);
        OrderCancel orderCancel = findByOrderId(orderId);
        Order order = orderService.findById(orderId);
        if (orderCancel.isApproved()) {
            log.error("order cancel already is approved by id: {}", orderId);
            throw new OrderCancelFailException("order cancel already is approved by id: " + orderId);
        }
        order.setStatus(OrderStatus.CANCELED);
        orderCancel.setApproved(true);
        orderCancel.setCanceledDateAt(LocalDateTime.now());
        orderRepository.save(order);
        orderCancelRepository.save(orderCancel);
        log.info("successfully approved order cancel request by order id: {}", orderId);
    }

    @Override
    public OrderCancel findByOrderId(long orderId) {
        log.info("find order cancel by id: {}", orderId);
        return orderCancelRepository.findByOrderId(orderId).orElseThrow(() -> {
            log.error("not found order cancel by order id: {}", orderId);
            throw new OrderCancelNotFoundException("not found order cancel by id: " + orderId);
        });
    }
}
