package com.black.mulberry.core.service.support;

import com.black.mulberry.core.entity.User;
import com.black.mulberry.core.exception.RepeatUsersException;
import com.black.mulberry.core.exception.UserNotFoundException;
import com.black.mulberry.core.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceSupport {

    private final UserRepository userRepository;

    public void ifPresentOrElseThrow(long userId) {
        log.info("Find by id user");
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isEmpty()) {
            log.error("User with id: {} not found", userId);
            throw new UserNotFoundException("User with id: " + userId + " NOT FOUND");
        }
    }

    public void ifUsersNotEqualsOrElseThrow(long user, long secondUser) {
        if (user == secondUser) {
            log.error("the user id: {} and second user id: {} is equals", user, secondUser);
            throw new RepeatUsersException("the user id: {} and second user id: {} is equals");
        }
    }
}
