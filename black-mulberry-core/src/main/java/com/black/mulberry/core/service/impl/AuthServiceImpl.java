package com.black.mulberry.core.service.impl;

import com.black.mulberry.core.entity.User;
import com.black.mulberry.core.exception.AuthenticatedException;
import com.black.mulberry.core.exception.UserEmailConflict;
import com.black.mulberry.core.mapper.UserMapper;
import com.black.mulberry.core.mapper.UserRegistrationMapper;
import com.black.mulberry.core.repository.UserRepository;
import com.black.mulberry.core.service.AuthService;
import com.black.mulberry.core.service.MailService;
import com.black.mulberry.core.util.JwtTokenUtil;
import com.black.mulberry.data.transfer.model.UserRole;
import com.black.mulberry.data.transfer.request.UserAuthRequest;
import com.black.mulberry.data.transfer.request.UserRegistrationRequest;
import com.black.mulberry.data.transfer.response.UserAuthResponse;
import com.black.mulberry.data.transfer.response.UserRegistrationResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.MailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;
    private final UserRegistrationMapper userRegistrationMapper;
    private final UserRepository userRepository;

    private final JwtTokenUtil jwtTokenUtil;

    private final MailSender mailSender;

    private final MailService mailService;

    @Override
    public UserAuthResponse auth(final UserAuthRequest userAuthRequest) {
        log.info("Request from user {} to get authenticated", userAuthRequest.getEmail());
        Optional<User> optionalUser = userRepository.findByEmail(userAuthRequest.getEmail());

        if (optionalUser.isEmpty()
                || !passwordEncoder.matches(userAuthRequest.getPassword(), optionalUser.get().getPassword())) {
            log.debug("{}: Provided wrong credentials for authentication", userAuthRequest.getEmail());
            throw new AuthenticatedException(userAuthRequest.getEmail() + ": Provided wrong credentials for authentication");
        }
            log.info("Succeed get user by email: {}", userAuthRequest.getEmail());
            return UserAuthResponse.builder()
                    .token(jwtTokenUtil.generateToken(userAuthRequest.getEmail()))
                    .user(userMapper.toResponse(optionalUser.get()))
                    .build();
    }

    @Override
    public UserRegistrationResponse registration(final UserRegistrationRequest userRequest) {
        log.info("Request from user {} to registration", userRequest.getEmail());
        Optional<User> byEmail = userRepository.findByEmail(userRequest.getEmail());
        if (byEmail.isPresent()) {
            log.debug("User with email: {} already exists", userRequest.getEmail());
            throw new UserEmailConflict("User with email: " + userRequest.getEmail() + " already exists");
        }
        User user = userRegistrationMapper.toEntity(userRequest);
        user.setRole(UserRole.USER);
        user.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        userRepository.save(user);
        mailService.sendEmail(user.getEmail(), "Welcome",
                "Hi " + user.getName() + " \n" +
                        "You have successfully registered!!!" +
                        " Press <a href=\"localhost:8080/loginPage\">Login</a> for login.");
        log.info("Succeed registered user by email: {}", userRequest.getEmail());
        return userRegistrationMapper.toResponse(user);
    }
}
