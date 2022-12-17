package com.black.mulberry.core.service.impl;

import com.black.mulberry.core.entity.Order;
import com.black.mulberry.core.entity.ProductBasket;
import com.black.mulberry.core.entity.User;
import com.black.mulberry.core.exception.OrderCompletedFailException;
import com.black.mulberry.core.exception.OrderConfirmedFailException;
import com.black.mulberry.core.exception.OrderNotFoundException;
import com.black.mulberry.core.exception.OrderOpenFailException;
import com.black.mulberry.core.repository.OrderRepository;
import com.black.mulberry.core.repository.ProductBasketRepository;
import com.black.mulberry.core.service.OrderService;
import com.black.mulberry.core.service.ProductBasketService;
import com.black.mulberry.core.service.UserService;
import com.black.mulberry.core.support.OrderSupport;
import com.black.mulberry.core.support.PaymentSupport;
import com.black.mulberry.core.support.ProductBasketSupport;
import com.black.mulberry.core.support.UserSupport;
import com.black.mulberry.data.transfer.model.OrderStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final ProductBasketService productBasketService;
    private final ProductBasketSupport productBasketSupport;
    private final OrderRepository orderRepository;
    private final ProductBasketRepository productBasketRepository;
    private final UserSupport userServiceSupport;
    private final OrderSupport orderServiceSupport;
    private final UserService userService;
    private PaymentSupport paymentSupport;

    @Autowired
    public void setPaymentSupport(@Lazy PaymentSupport paymentSupport) {
        this.paymentSupport = paymentSupport;
    }

    @Override
    public Order open(long userId) {
        log.info("request open order by user id: {}", userId);
        User user = userService.findById(userId);
        ProductBasket basketByActual = productBasketService.findBasketByActual(userId);
        if (productBasketSupport.isEmpty(basketByActual.getId())) {
            log.error("Unable to order because the cart is empty from user id: {}", userId);
            throw new OrderOpenFailException("Unable to order because the cart is empty from user id: " + userId);
        }
        orderServiceSupport.ifPresentThrow(basketByActual.getId(), userId);
        basketByActual.setActual(false);

        Order order = Order.builder()
                .basket(basketByActual)
                .user(user)
                .status(OrderStatus.OPEN)
                .openDateAt(LocalDateTime.now())
                .build();

        productBasketRepository.save(basketByActual);
        Order save = orderRepository.save(order);
        log.info("successfully open order id: {}", order.getId());
        return save;
    }

    @Override
    public void confirmed(long orderId) {
        log.info("request confirmed order id: {}", orderId);
        Order order = findById(orderId);
        if (order.getStatus() == OrderStatus.OPEN) {
            log.error("order already confirmed id: {}", orderId);
            throw new OrderConfirmedFailException("order already confirmed id: " + orderId);
        } else if (order.getStatus() != OrderStatus.OPEN) {
            log.error("order confirmed fail by order id: {}", orderId);
            throw new OrderConfirmedFailException("order confirmed fail by order id: " + orderId);
        } else if (paymentSupport.isPaidByOrderId(orderId)) {
            log.error("order is not paid id: {}", orderId);
            throw new OrderConfirmedFailException("order is not paid id: " + orderId);
        }
        order.setStatus(OrderStatus.CONFIRMED);
        order.setConfirmedDateAt(LocalDateTime.now());
        orderRepository.save(order);
        log.info("successes confirmed order id: {}", orderId);
    }

    @Override
    public void completed(long orderId) {
        log.info("Request completed order by id: {}", orderId);
        Order order = findById(orderId);
        if (order.getStatus() == OrderStatus.COMPLETED) {
            log.error("order already completed id: {}", orderId);
            throw new OrderCompletedFailException("order already completed id: " + orderId);
        } else if (order.getStatus() != OrderStatus.CONFIRMED) {
            log.error("order completed fail by order id: {}", orderId);
            throw new OrderCompletedFailException("order completed fail by order id: " + orderId);
        }
        order.setCompletedDateAt(LocalDateTime.now());
        order.setStatus(OrderStatus.COMPLETED);
        orderRepository.save(order);
        log.info("successes completed order id: {}", orderId);
    }

    @Override
    public void findAllOpenedByUserId(long userId) {
        userServiceSupport.ifPresentOrElseThrow(userId);
        orderRepository.findByUserIdAndStatusEquals(userId, OrderStatus.OPEN);
    }

    @Override
    public Order findByIdAndUserId(long orderId, long userId) {
        log.info("find order by id: {}, and user id: {}", orderId, userId);
        return orderRepository.findByIdAndUserId(orderId, userId).orElseThrow(() -> {
            log.error("order not found by id: {} and user id: {}", orderId, userId);
            throw new OrderNotFoundException("order not found by id: " + orderId + " and user id: " + userId);
        });
    }

    @Override
    public Order findById(long id) {
        log.info("find order by id: {}", id);
        return orderRepository.findById(id).orElseThrow(() -> {
            log.error("order not found by id: {}", id);
            throw new OrderNotFoundException("order not found by id: " + id);
        });
    }
}
