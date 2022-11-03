package com.black.mulberry.core.service.impl;

import com.black.mulberry.core.entity.User;
import com.black.mulberry.core.exception.UserNotFoundException;
import com.black.mulberry.core.mapper.UserMapper;
import com.black.mulberry.core.repository.UserRepository;
import com.black.mulberry.core.service.UserService;
import com.black.mulberry.data.transfer.request.UserRequest;
import com.black.mulberry.data.transfer.response.UserResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public UserResponse update(UserRequest userRequest) {
        log.info("Request from user {} to update", userRequest.getEmail());
        Optional<User> optionalUser = userRepository.findByEmail(userRequest.getEmail());
        if (optionalUser.isEmpty()) {
            log.debug("User with email {} already exists", userRequest.getEmail());
            throw new UserNotFoundException("User with email: " + userRequest.getEmail() + " already exists");
        }
        User user = optionalUser.get();
        user.setName(userRequest.getName());
        user.setSurname(userRequest.getSurname());
        User save = userRepository.save(user);
        log.info("Succeed updated data from user {}", userRequest.getEmail());
        return userMapper.toResponse(save);
    }

    @Override
    public void deleteById(long id) {
        log.info("Request delete by id: {} user", id);
        userRepository.deleteById(id);
    }

    @Override
    public UserResponse findById(long id) {
        log.info("Find by id user");
        User user = userRepository.findById(id).orElseThrow(() -> {
            log.debug("User with id: {} not found", id);
            return new UserNotFoundException("User with id: " + id + " NOT FOUND");
        });
        return userMapper.toResponse(user);
    }

    @Override
    public UserResponse findByEmail(String email) {
        log.info("Find by email user");
        User user = userRepository.findByEmail(email).orElseThrow(() -> {
            log.debug("User with email: {} not found", email);
            return new UserNotFoundException("User with email: " + email + " NOT FOUND");
        });
        return userMapper.toResponse(user);
    }

    @Override
    public List<UserResponse> findAll() {
        log.info("Find all list Users");
        List<User> userList = userRepository.findAll();
        return userList.stream()
                .map(userMapper::toResponse)
                .collect(Collectors.toCollection(LinkedList::new));
    }

}
