package com.black.mulberry.core.support;

import com.black.mulberry.core.entity.User;
import com.black.mulberry.core.exception.RepeatUsersException;
import com.black.mulberry.core.exception.UserNotFoundException;
import com.black.mulberry.core.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * The UserSupport class provides methods for checking user-related information.
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class UserSupport {

    private final UserRepository userRepository;

    /**
     * Checks if a user with a given ID exists, and throws an exception if it doesn't.
     *
     * @param userId the ID of the user to check
     * @throws UserNotFoundException if a user with the given ID doesn't exist
     */
    public void ifPresentOrElseThrow(long userId) {
        log.info("Finding user by ID: {}", userId);
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isEmpty()) {
            log.error("User with ID {} not found", userId);
            throw new UserNotFoundException("User with ID " + userId + " not found");
        }
    }

    /**
     * Checks if two user IDs are equal, and throws an exception if they are.
     *
     * @param user       the ID of the first user
     * @param secondUser the ID of the second user
     * @throws RepeatUsersException if the two user IDs are equal
     */
    public void ifUsersNotEqualsOrElseThrow(long user, long secondUser) {
        if (user == secondUser) {
            log.error("The user ID {} and second user ID {} are equal", user, secondUser);
            throw new RepeatUsersException("The user ID " + user + " and second user ID " + secondUser + " are equal");
        }
    }
}
