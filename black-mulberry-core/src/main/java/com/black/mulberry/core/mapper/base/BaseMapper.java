package com.black.mulberry.core.mapper.base;

import com.black.mulberry.core.entity.User;
import com.black.mulberry.data.transfer.model.CreateUserDto;

public interface BaseMapper<Entity, Request, Response> {

    Entity toEntity(Request request);

    Response toResponse(Entity entity);
}
