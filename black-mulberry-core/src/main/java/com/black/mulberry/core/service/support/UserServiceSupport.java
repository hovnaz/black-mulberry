package com.black.mulberry.core.service.support;

import com.black.mulberry.core.entity.User;
import com.black.mulberry.core.exception.UserNotFoundException;
import com.black.mulberry.core.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceSupport {
    private final UserRepository userRepository;

    public User findById(long id) {
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User with id: " + id + " NOT FOUND"));
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(() -> new UserNotFoundException("User with email: " + email + " NOT FOUND"));
    }
}
