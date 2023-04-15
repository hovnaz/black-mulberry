package com.black.mulberry.core.service;

import com.black.mulberry.core.entity.Order;

/**
 * The OrderService interface provides methods for managing orders related to users.
 */
public interface OrderService {

    /**
     * Opens an order for a given user with an actual basket.
     *
     * @param userId the ID of the user associated with the order
     * @return the opened Order object
     */
    Order open(long userId);

    /**
     * Marks an order as completed for a given order ID.
     *
     * @param orderId the ID of the order to be marked as completed
     */
    void completed(long orderId);

    /**
     * Confirms an order as completed for a given order ID.
     *
     * @param orderId the ID of the order to be confirmed as completed
     */
    void confirmed(long orderId);

    /**
     * Finds all open Order objects for a given user ID.
     *
     * @param userId the ID of the user associated with the orders
     */
    void findAllOpenedByUserId(long userId);

    /**
     * Finds an Order object by its ID and the ID of the associated user.
     *
     * @param orderId the ID of the Order object
     * @param userId  the ID of the user associated with the order
     * @return the Order object with the given ID and associated user ID
     */
    Order findByIdAndUserId(long orderId, long userId);

    /**
     * Finds an Order object by its ID.
     *
     * @param id the ID of the Order object
     * @return the Order object with the given ID
     */
    Order findById(long id);
}
