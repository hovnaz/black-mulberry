package com.black.mulberry.core.security;

import com.black.mulberry.core.entity.User;
import com.black.mulberry.core.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

/**
 * An implementation of Spring Security's UserDetailsService interface that retrieves user details from a UserService
 * object and returns a custom CurrentUser object that contains additional information about the current user.
 */
@Service
@RequiredArgsConstructor
public class CurrentUserDetailServiceImpl implements UserDetailsService {

    private final UserService userService;

    /**
     * Loads the user details for the specified username by retrieving a User object from the UserService and returning
     * a custom CurrentUser object that contains additional information about the user.
     *
     * @param username the username of the user whose details to load
     * @return a UserDetails object containing the user's details
     */
    @Override
    public UserDetails loadUserByUsername(String username) {
        User user = userService.findByEmail(username);
        return new CurrentUser(user);
    }
}
