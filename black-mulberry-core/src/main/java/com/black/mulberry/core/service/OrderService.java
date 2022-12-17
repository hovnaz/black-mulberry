package com.black.mulberry.core.service;

import com.black.mulberry.core.entity.Order;

public interface OrderService {

    /**
     * open order for actual basket
     *
     * @param userId by id
     * @return opened order
     */
    Order open(long userId);

    /**
     * completed order after opened
     *
     * @param orderId by id
     */
    void completed(long orderId);

    /**
     * confirmed order after completed
     *
     * @param orderId by id
     */
    void confirmed(long orderId);

    void findAllOpenedByUserId(long userId);

    Order findByIdAndUserId(long orderId, long userId);

    Order findById(long id);
}
