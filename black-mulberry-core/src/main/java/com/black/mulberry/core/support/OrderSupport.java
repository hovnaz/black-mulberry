package com.black.mulberry.core.support;

import com.black.mulberry.core.entity.Order;
import com.black.mulberry.core.exception.OrderConflictException;
import com.black.mulberry.core.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderSupport {

    private final OrderRepository orderRepository;

    public void ifPresentThrow(long basketId, long userId) {
        log.info("check if present order by basket id: {} and user id: {}", basketId, userId);
        Optional<Order> orderOptional = orderRepository.findByBasketIdAndUserId(basketId, userId);
        if (orderOptional.isPresent()) {
            log.error("order conflict exception from basket: id {} and user id: {}", basketId, userId);
            throw new OrderConflictException("order conflict exception from basket: id " + basketId + " and user id: " + userId);
        }
    }
}
