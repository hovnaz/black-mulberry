package com.black.mulberry.core.mapper;

import com.black.mulberry.core.entity.User;
import com.black.mulberry.core.mapper.base.BaseMapper;
import com.black.mulberry.data.transfer.request.UserRegistrationRequest;
import com.black.mulberry.data.transfer.response.UserRegistrationResponse;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserRegistrationMapper implements BaseMapper<User, UserRegistrationRequest, UserRegistrationResponse> {

    private final ModelMapper modelMapper;

    @Override
    public User toEntity(UserRegistrationRequest userRegistrationRequest) {
        return modelMapper.map(userRegistrationRequest, User.class);
    }

    @Override
    public UserRegistrationResponse toResponse(User user) {
        return modelMapper.map(user, UserRegistrationResponse.class);
    }
}
