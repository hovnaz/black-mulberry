package com.black.mulberry.core.exception;

public class OrderCancelNotFoundException extends RuntimeException {

    public OrderCancelNotFoundException(String message) {
        super(message);
    }
}
