package com.black.mulberry.core.service;

import com.black.mulberry.core.entity.Order;
import com.black.mulberry.core.entity.OrderCancel;
import com.black.mulberry.data.transfer.model.OrderStatus;

public interface OrderCancelService {

    /**
     * add request cansel order
     * can't open request except completed
     *
     * @param orderId     by order id
     * @param description a description of why it was cancelled
     * @see OrderCancel
     */
    void cancel(long orderId, String description);

    /**
     * approve request order cancel
     * make status order canceled
     *
     * @param orderId by order id
     * @see Order
     * @see OrderStatus CANCELED
     */
    void approveCancelled(long orderId);

    OrderCancel findByOrderId(long orderId);
}
