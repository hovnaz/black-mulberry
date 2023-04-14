package com.black.mulberry.core.security;

import com.black.mulberry.core.entity.User;
import org.springframework.security.core.authority.AuthorityUtils;

/**
 * A custom user class that extends Spring Security's User class. This class contains additional information about the
 * current user that is not included in the standard User class, such as the User object itself and the user's ID.
 */
public class CurrentUser extends org.springframework.security.core.userdetails.User {

    private final User user;

    /**
     * Constructs a new CurrentUser object based on the specified User object.
     *
     * @param user the User object to use for constructing the CurrentUser
     */
    public CurrentUser(User user) {
        super(user.getEmail(), user.getPassword(),
                AuthorityUtils.createAuthorityList(user.getRole().name()));
        this.user = user;
    }

    /**
     * Returns the User object associated with the current user.
     *
     * @return the User object associated with the current user
     */
    public User getUser() {
        return user;
    }

    /**
     * Returns the ID of the current user.
     *
     * @return the ID of the current user
     */
    public long getId() {
        return user.getId();
    }
}
