package com.black.mulberry.rest.endpoint;

import com.black.mulberry.core.entity.User;
import com.black.mulberry.core.mapper.UserMapper;
import com.black.mulberry.core.mapper.base.BaseMapper;
import com.black.mulberry.core.repository.UserRepository;
import com.black.mulberry.data.transfer.model.CreateUserDto;
import com.black.mulberry.data.transfer.model.UserRole;
import com.black.mulberry.rest.util.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@Slf4j
public class UserEndpoint {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    private final BaseMapper baseMapper;

    private final JwtTokenUtil jwtTokenUtil;

    @PostMapping("/user")
    public ResponseEntity<?> register(@RequestBody CreateUserDto createUserDto) {
        Optional<User> existingUser = userRepository.findByEmail(createUserDto.getEmail());
        if (existingUser.isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }

        User user = baseMapper.map(createUserDto);
        user.setRole(UserRole.USER);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return ResponseEntity.ok().build();
    }

//    @PostMapping("/user/auth")
//    public ResponseEntity<?> auth(@RequestBody UserAuthDto userAuthDto) {
//        Optional<User> byEmail = userRepository.findByEmail(userAuthDto.getEmail());
//        if (byEmail.isPresent()) {
//            User user = byEmail.get();
//            if (passwordEncoder.matches(userAuthDto.getPassword(), user.getPassword())) {
//                log.info("User with username {} get auth token", user.getEmail());
//                return ResponseEntity.ok(UserAuthResponseDto.builder()
//                        .token(jwtTokenUtil.generateToken(user.getEmail()))
//                        .user(userMapper.map(user))
//                        .build()
//                );
//            }
//        }
//        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
//    }
}