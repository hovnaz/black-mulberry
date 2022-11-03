package com.black.mulberry.core.service;

import com.black.mulberry.data.transfer.request.UserAuthRequest;
import com.black.mulberry.data.transfer.request.UserRegistrationRequest;
import com.black.mulberry.data.transfer.response.UserAuthResponse;
import com.black.mulberry.data.transfer.response.UserRegistrationResponse;

public interface AuthService {
    /**
     * find user detail token and userResponse
     *
     * @param userAuthRequest
     * @return UserAuthRequest (token and UserResponse )
     */
    UserAuthResponse auth(UserAuthRequest userAuthRequest);

    /**
     * New user
     *
     * @param userRegistrationRequest
     * @return UserRegistrationResponse
     */
    UserRegistrationResponse registration(UserRegistrationRequest userRegistrationRequest);

}
