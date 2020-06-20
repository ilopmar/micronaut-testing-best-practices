package com.example.exceptions;

import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.hateoas.JsonError;
import io.micronaut.http.server.exceptions.ExceptionHandler;

import javax.inject.Singleton;

/**
 * Handles {@link UserUnauthorizedException} and returns 401.
 */
@Singleton
public class UserUnauthorizedExceptionHandler implements ExceptionHandler<UserUnauthorizedException, HttpResponse<JsonError>> {

    @Override
    public HttpResponse<JsonError> handle(HttpRequest request, UserUnauthorizedException exception) {
        return HttpResponse.unauthorized();
    }
}
