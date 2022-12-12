package com.black.mulberry.core.security;

import com.black.mulberry.core.entity.User;
import org.springframework.security.core.authority.AuthorityUtils;

/**
 * Returns the current user object. This can be User, UserDetails or your custom user object.
 * You will need to cast the return object to UserDetails or your own user object if it is a custom one.
 */
public class CurrentUser extends org.springframework.security.core.userdetails.User {

    private final User user;

    public CurrentUser(User user) {
        super(user.getEmail(), user.getPassword(),
                AuthorityUtils.createAuthorityList(user.getRole().name()));
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public long getId() {
        return user.getId();
    }
}
