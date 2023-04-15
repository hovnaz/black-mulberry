package com.black.mulberry.core.service;

import com.black.mulberry.data.transfer.request.UserAuthRequest;
import com.black.mulberry.data.transfer.request.UserRegistrationRequest;
import com.black.mulberry.data.transfer.response.UserAuthResponse;
import com.black.mulberry.data.transfer.response.UserRegistrationResponse;

/**
 * A service interface for authenticating and registering users.
 */
public interface AuthService {

    /**
     * Authenticates a user based on the provided authentication request and returns a response containing the user's
     * authentication token and user details.
     *
     * @param userAuthRequest the authentication request containing the user's email and password
     * @return a UserAuthResponse object containing the user's authentication token and user details
     */
    UserAuthResponse auth(UserAuthRequest userAuthRequest);

    /**
     * Registers a new user based on the provided registration request and returns a response containing the newly
     * registered user's details.
     *
     * @param userRegistrationRequest the registration request containing the new user's details
     * @return a UserRegistrationResponse object containing the newly registered user's details
     */
    UserRegistrationResponse registration(UserRegistrationRequest userRegistrationRequest);

}
