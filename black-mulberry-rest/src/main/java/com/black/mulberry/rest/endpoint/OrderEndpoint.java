package com.black.mulberry.rest.endpoint;

import com.black.mulberry.core.service.OrderCancelService;
import com.black.mulberry.core.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/order")
@PreAuthorize("hasAuthority('ADMIN')")
public class OrderEndpoint {

    private final OrderService orderService;
    private final OrderCancelService orderCancelService;

    @PostMapping("/confirmed")
    public void confirmed(@RequestBody long orderId) {
        orderService.confirmed(orderId);
    }

    @PostMapping("/completed")
    public void completed(@RequestBody long orderId) {
        orderService.completed(orderId);
    }

    @PostMapping("/cancel")
    public void cancel(@RequestBody long orderId, @RequestBody String description) {
        orderCancelService.cancel(orderId, description);
    }

    @PostMapping("/cancel/approve")
    public void cancelApprove(@RequestBody long orderId) {
        orderCancelService.approveCancelled(orderId);
    }
}
