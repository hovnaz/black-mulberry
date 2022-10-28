package com.black.mulberry.core.mapper;

import com.black.mulberry.core.entity.User;
import com.black.mulberry.core.mapper.base.BaseMapper;
import com.black.mulberry.data.transfer.request.UserRequest;
import com.black.mulberry.data.transfer.response.UserResponse;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

/**
 * @see org.modelmapper.ModelMapper
 */
@Service
@RequiredArgsConstructor
public class UserMapper implements BaseMapper<User, UserRequest, UserResponse> {

    private final ModelMapper modelMapper;

    @Override
    public User toEntity(UserRequest userRequest) {
        return modelMapper.map(userRequest, User.class);
    }

    @Override
    public UserResponse toResponse(User user) {
        return modelMapper.map(user, UserResponse.class);
    }
}
