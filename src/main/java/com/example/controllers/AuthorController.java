package com.example.controllers;

import com.example.model.Author;
import com.example.services.AuthorService;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.Status;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Controller("/authors")
public class AuthorController {

    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @Post
    @Status(HttpStatus.CREATED)
    public Author createAuthor(@NotNull @Valid @Body CreateAuthorRequest createAuthorRequest) {
        return authorService.saveAuthor(createAuthorRequest.getName());
    }

}
