package com.example.controllers;

import com.example.exceptions.UserUnauthorizedException;
import com.example.model.Author;
import com.example.services.AuthorService;
import com.example.security.SecurityService;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.QueryValue;
import io.micronaut.http.annotation.Status;

import javax.annotation.Nullable;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Controller("/authors")
public class AuthorController {

    private final AuthorService authorService;
    private final SecurityService securityService;

    public AuthorController(AuthorService authorService,
                            SecurityService securityService) {
        this.authorService = authorService;
        this.securityService = securityService;
    }

    @Post
    @Status(HttpStatus.CREATED)
    public Author createAuthor(@NotNull @Valid @Body CreateAuthorRequest createAuthorRequest) {
        return authorService.saveAuthor(createAuthorRequest.getName());
    }

    @Get("/by-name")
    public Author findAuthorByName(@NotBlank @QueryValue("author") String author,
                                   @QueryValue("username") @Nullable String username) {
        if (!securityService.canUserAccess(username)) {
            throw new UserUnauthorizedException();
        }

        // If the author is not found this will return `null`. Micronaut will "transform" the null in a 404 response.
        return authorService
                .findAuthorByName(author)
                .orElse(null);
    }
}
