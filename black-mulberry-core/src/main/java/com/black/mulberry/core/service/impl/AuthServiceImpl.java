package com.black.mulberry.core.service.impl;

import com.black.mulberry.core.entity.User;
import com.black.mulberry.core.exception.UserEmailConflict;
import com.black.mulberry.core.exception.UserNotFoundException;
import com.black.mulberry.core.mapper.UserMapper;
import com.black.mulberry.core.mapper.UserRegistrationMapper;
import com.black.mulberry.core.repository.UserRepository;
import com.black.mulberry.core.service.AuthService;
import com.black.mulberry.core.util.UserServiceUtil;
import com.black.mulberry.data.transfer.model.UserRole;
import com.black.mulberry.data.transfer.request.UserAuthRequest;
import com.black.mulberry.data.transfer.request.UserRegistrationRequest;
import com.black.mulberry.data.transfer.response.UserAuthResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final PasswordEncoder passwordEncoder;
    private final UserServiceUtil userServiceUtil;
    private final UserMapper userMapper;
    private final UserRegistrationMapper userRegistrationMapper;
    private final UserRepository userRepository;

    @Override
    public UserAuthResponse auth(UserAuthRequest userAuthRequest) {
        User user = userServiceUtil.findByEmail(userAuthRequest.getEmail());
        if (passwordEncoder.matches(userAuthRequest.getPassword(), user.getPassword())) {
            return UserAuthResponse.builder()
                    .user(userMapper.toResponse(user))
                    .build();
        }
        // The user password is incorrect
        throw new UserNotFoundException("User password is incorrect");

    }

    @Override
    public void registration(UserRegistrationRequest userRequest) {
        Optional<User> byEmail = userRepository.findByEmail(userRequest.getEmail());
        if (byEmail.isPresent()) {
            throw new UserEmailConflict("User with email: " + userRequest.getEmail() + " already exists");
        }
        User user = userRegistrationMapper.toEntity(userRequest);
        user.setRole(UserRole.USER);
        user.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        userRepository.save(user);
    }
}
