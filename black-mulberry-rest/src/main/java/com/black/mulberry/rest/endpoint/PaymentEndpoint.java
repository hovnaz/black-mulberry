package com.black.mulberry.rest.endpoint;

import com.black.mulberry.core.facade.PaymentFacade;
import com.black.mulberry.core.security.CurrentUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/payment")
@PreAuthorize("isAuthenticated()")
public class PaymentEndpoint {

    private final PaymentFacade paymentFacade;

    @PostMapping("/pay")
    public void pay(@AuthenticationPrincipal CurrentUser currentUser){
        paymentFacade.pay(currentUser.getId());
    }
}
