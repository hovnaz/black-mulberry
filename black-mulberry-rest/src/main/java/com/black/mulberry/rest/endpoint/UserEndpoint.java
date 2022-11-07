package com.black.mulberry.rest.endpoint;

import com.black.mulberry.core.service.AuthService;
import com.black.mulberry.data.transfer.request.UserAuthRequest;
import com.black.mulberry.data.transfer.request.UserRegistrationRequest;
import com.black.mulberry.data.transfer.response.UserAuthResponse;
import com.black.mulberry.data.transfer.response.UserRegistrationResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/user")
public class UserEndpoint {

    private final AuthService authService;

    @GetMapping("/auth")
    public ResponseEntity<?> userAuth(@RequestBody UserAuthRequest userAuthRequest) {
        UserAuthResponse auth = authService.auth(userAuthRequest);
        return ResponseEntity.ok(auth);
    }

    @PostMapping("/register")
    public ResponseEntity<?> userRegister(@RequestBody UserRegistrationRequest userRegistrationRequest) {
        UserRegistrationResponse register = authService.registration(userRegistrationRequest);
        return ResponseEntity.ok(register);
    }
}
