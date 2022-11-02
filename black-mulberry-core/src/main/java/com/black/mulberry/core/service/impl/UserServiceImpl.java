package com.black.mulberry.core.service.impl;

import com.black.mulberry.core.entity.User;
import com.black.mulberry.core.mapper.UserMapper;
import com.black.mulberry.core.repository.UserRepository;
import com.black.mulberry.core.service.UserService;
import com.black.mulberry.core.service.support.UserServiceSupport;
import com.black.mulberry.data.transfer.request.UserRequest;
import com.black.mulberry.data.transfer.response.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final UserServiceSupport userServiceSupport;

    @Override
    public UserResponse update(UserRequest userRequest) {
        long userId = userRequest.getId();
        User user = userServiceSupport.findById(userId);
        user.setName(userRequest.getName());
        user.setSurname(userRequest.getSurname());
        User save = userRepository.save(user);
        return userMapper.toResponse(save);
    }

    @Override
    public void deleteById(long id) {
        userRepository.deleteById(id);
    }

    @Override
    public UserResponse findById(long id) {
        User user = userServiceSupport.findById(id);
        return userMapper.toResponse(user);
    }

    @Override
    public UserResponse findByEmail(String email) {
        User user = userServiceSupport.findByEmail(email);
        return userMapper.toResponse(user);
    }

    @Override
    public List<UserResponse> findAll() {
        List<User> userList = userRepository.findAll();
        return userList.stream()
                .map(userMapper::toResponse)
                .collect(Collectors.toCollection(LinkedList::new));
    }

}
