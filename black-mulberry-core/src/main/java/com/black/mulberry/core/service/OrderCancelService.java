package com.black.mulberry.core.service;

import com.black.mulberry.core.entity.Order;
import com.black.mulberry.core.entity.OrderCancel;
import com.black.mulberry.data.transfer.model.OrderStatus;

/**
 * Provides methods for cancelling an order.
 */
public interface OrderCancelService {

    /**
     * Creates a request to cancel the specified order.
     * The request cannot be opened unless the order status is "Completed".
     *
     * @param orderId     the ID of the order to cancel
     * @param description a description of why the order was cancelled
     * @see OrderCancel
     */
    void cancel(long orderId, String description);

    /**
     * Approves the request to cancel the specified order.
     * The order status will be changed to "Cancelled".
     *
     * @param orderId the ID of the order to cancel
     * @see Order
     * @see OrderStatus#CANCELED
     */
    void approveCancelled(long orderId);

    /**
     * Finds the order cancellation request by the specified order ID.
     *
     * @param orderId the ID of the order
     * @return the {@link OrderCancel} object corresponding to the specified order ID,
     *         or null if the order cancellation request does not exist
     */
    OrderCancel findByOrderId(long orderId);
}
