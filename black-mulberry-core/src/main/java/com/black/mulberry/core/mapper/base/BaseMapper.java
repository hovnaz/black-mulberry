package com.black.mulberry.core.mapper.base;

/**
 * BaseMapper interface for mapping between request and response objects and entity objects.
 * This interface defines two methods for mapping between request and response objects and entity objects.
 *
 * @param <Entity>   the type of the entity object
 * @param <Request>  the type of the request object
 * @param <Response> the type of the response object
 */
public interface BaseMapper<Entity, Request, Response> {

    /**
     * Maps a request object to an entity object.
     *
     * @param request the request object
     * @return the corresponding entity object
     */
    Entity toEntity(Request request);

    /**
     * Maps an entity object to a response object.
     *
     * @param entity the entity object
     * @return the corresponding response object
     */
    Response toResponse(Entity entity);
}
