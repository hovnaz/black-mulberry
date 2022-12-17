package com.black.mulberry.core.facade.impl;

import com.black.mulberry.core.entity.Order;
import com.black.mulberry.core.entity.Payment;
import com.black.mulberry.core.facade.PaymentFacade;
import com.black.mulberry.core.service.OrderService;
import com.black.mulberry.core.service.PaymentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class PaymentFacadeImpl implements PaymentFacade {

    private final PaymentService paymentService;
    private final OrderService orderService;

    @Transactional
    @Override
    public void pay(long userId) {
        Order order = orderService.open(userId);
        Payment openedPayment = paymentService.open(userId, order.getId());
        paymentService.pay(openedPayment, userId);
    }
}
