package com.black.mulberry.core.service.impl;

import com.black.mulberry.core.entity.User;
import com.black.mulberry.core.exception.UserNotFoundException;
import com.black.mulberry.core.repository.UserRepository;
import com.black.mulberry.core.util.DataGenerator;
import com.black.mulberry.data.transfer.request.UserUpdateRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class UserServiceImplTest {

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserRepository userRepository;

    private User user;

    private UserUpdateRequest userUpdateRequest;

    @BeforeEach
    void init() {
        user = DataGenerator.generateUser();
        userUpdateRequest = DataGenerator.generateUserUpdateRequest();
    }

    @Test
    void update_successTest() {
        when(userRepository.findById(anyLong()))
                .thenReturn(Optional.of(new User()));
        when(userRepository.save(any(User.class)))
                .thenReturn(user);
        assertDoesNotThrow(() -> userService.update(userUpdateRequest, 555L));
        assertEquals(user.getSurname(), userUpdateRequest.getSurname());
    }

    @Test
    void update_throwsUserNotFoundException() {
        when(userRepository.findById(anyLong()))
                .thenReturn(Optional.empty());
        assertThrows(UserNotFoundException.class, () -> userService
                .update(DataGenerator.generateUserUpdateRequest(), 555L));
    }

    @Test
    void findById_successTest() {
        when(userRepository.findById(anyLong()))
                .thenReturn(Optional.of(new User()));
        assertDoesNotThrow(() -> userService.findById(555L));
    }

    @Test
    void findById_throwsUserNotFoundException() {
        when(userRepository.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(UserNotFoundException.class, () -> userService
                .findById(555L));
    }

    @Test
    void findByEmail_successTest() {
        when(userRepository.findByEmail(any()))
                .thenReturn(Optional.of(new User()));
        assertDoesNotThrow(() -> userService.findByEmail("poxos@gmail.com"));
    }

    @Test
    void findByEmail_throwsUserNotFoundException() {
        when(userRepository.findByEmail(any())).thenReturn(Optional.empty());
        assertThrows(UserNotFoundException.class, () -> userService
                .findByEmail("poxos@gmail.com"));
    }
}
