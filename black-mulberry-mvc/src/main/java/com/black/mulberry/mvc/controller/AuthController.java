package com.black.mulberry.mvc.controller;

import com.black.mulberry.core.service.AuthService;
import com.black.mulberry.data.transfer.request.UserRegistrationRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @GetMapping("/")
    public String authPage() {
        return "view/auth";
    }
    
    @PostMapping("/registration")
    public String registration(@ModelAttribute UserRegistrationRequest userRegistrationRequest) {
        authService.registration(userRegistrationRequest);
        return "redirect:/v1/auth/";
    }
}
