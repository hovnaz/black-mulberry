package com.black.mulberry.core.service.impl;

import com.black.mulberry.core.entity.Order;
import com.black.mulberry.core.entity.Payment;
import com.black.mulberry.core.entity.User;
import com.black.mulberry.core.exception.PaymentNotFoundException;
import com.black.mulberry.core.exception.PaymentOpenFailException;
import com.black.mulberry.core.exception.PaymentPaidFailException;
import com.black.mulberry.core.repository.PaymentRepository;
import com.black.mulberry.core.service.OrderService;
import com.black.mulberry.core.service.PaymentService;
import com.black.mulberry.core.service.ProductBasketService;
import com.black.mulberry.core.service.UserService;
import com.black.mulberry.core.support.PaymentSupport;
import com.black.mulberry.core.support.UserSupport;
import com.black.mulberry.data.transfer.model.OrderStatus;
import com.black.mulberry.data.transfer.model.PaymentStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@Slf4j
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final UserSupport userServiceSupport;
    private final UserService userService;
    private final OrderService orderService;
    private final PaymentSupport paymentServiceSupport;
    private final ProductBasketService productBasketService;
    private final PaymentRepository paymentRepository;

    @Override
    public Payment open(long userId, long orderId) {
        log.info("request open payment by order id: {} and user id: {}", orderId, userId);
        paymentServiceSupport.ifPresentThrow(userId, orderId);
        User user = userService.findById(userId);
        Order order = orderService.findByIdAndUserId(orderId, userId);
        if (order.getStatus() != OrderStatus.OPEN) {
            log.error("Order is not opened id: {} from user id: {}", orderId, userId);
            throw new PaymentOpenFailException("Order is not opened id: " + orderId + " from user id: " + userId);
        }

        Payment payment = Payment.builder()
                .user(user)
                .order(order)
                .status(PaymentStatus.UN_PAID)
                .amount(productBasketService.amountByUserIdAndBasketId(userId, order.getBasket().getId()))
                .build();
        Payment save = paymentRepository.save(payment);
        log.info("successfully opened payment by order id: {} and user id: {}", orderId, userId);
        return save;
    }

    @Override
    public void pay(Payment payment, long userId) {
        log.info("request paid by id: {} from user id: {}", payment.getId(), userId);
        userServiceSupport.ifPresentOrElseThrow(userId);
        if (!paymentServiceSupport.isUnPaid(payment)) {
            log.error("payment is not un paid payment id: {} from user id: {}", payment.getId(), userId);
            throw new PaymentPaidFailException("payment is not un paid payment id: " + payment.getId() + " from user id: " + userId);
        }
        payment.setPaymentDate(LocalDateTime.now());
        payment.setStatus(PaymentStatus.PAID);
        paymentRepository.save(payment);
        log.info("Payment has been made successfully by id: {} from user id: {}", payment.getId(), userId);
    }

    @Override
    public Payment findByOrderId(long orderId) {
        log.info("find payment by order id: {}", orderId);
        return paymentRepository.findByOrderId(orderId).orElseThrow(() -> {
            log.error("not found payment by order id: {}", orderId);
            throw new PaymentNotFoundException("Not found by order id: " + orderId);
        });
    }

    @Override
    public Payment findByIdAndUserId(long paymentId, long userId) {
        log.info("find payment by payment id: {} and user id: {}", paymentId, userId);
        return paymentRepository.findByIdAndUserId(paymentId, userId).orElseThrow(() -> {
            log.error("not found payment by id: {} and user id: {}", paymentId, userId);
            throw new PaymentNotFoundException("Not found by id: " + paymentId + "user id : " + userId);
        });
    }
}
