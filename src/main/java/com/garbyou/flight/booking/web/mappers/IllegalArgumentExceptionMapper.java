package com.garbyou.flight.booking.web.mappers;

import com.google.inject.Singleton;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * Maps a {@link IllegalArgumentException} to a HTTP 400 (Bad Request)
 */
@Provider
@Singleton
public class IllegalArgumentExceptionMapper implements ExceptionMapper<IllegalArgumentException> {

    @Override
    public final Response toResponse(final IllegalArgumentException e) {
        return Response.status(Response.Status.BAD_REQUEST.getStatusCode()).entity(e.getMessage()).build();
    }
}
