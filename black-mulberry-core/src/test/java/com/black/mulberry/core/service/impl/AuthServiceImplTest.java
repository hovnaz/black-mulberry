package com.black.mulberry.core.service.impl;

import com.black.mulberry.core.entity.User;
import com.black.mulberry.core.repository.UserRepository;
import com.black.mulberry.core.util.DataGenerator;
import com.black.mulberry.core.util.JwtTokenUtil;
import com.black.mulberry.data.transfer.request.UserAuthRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class AuthServiceImplTest {

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    PasswordEncoder passwordEncoder;

    @Mock
    private JwtTokenUtil jwtTokenUtil;

    private User user;

    private UserAuthRequest userAuthRequest;

    @BeforeEach
    void init() {
        user = DataGenerator.generateUser();
        userAuthRequest = DataGenerator.generateUserAuthRequest();
    }

    @Test
    void auth_successTest() {
        when(userRepository.findByEmail(any()))
                .thenReturn(Optional.of(new User()));
        when(userRepository.save(any(User.class)))
                .thenReturn(user);
        assertDoesNotThrow(() -> userService.findByEmail("test@mail.com"));
        assertEquals(user.getEmail(), userAuthRequest.getEmail());
    }
}
