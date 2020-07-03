package com.example.controllers;

import com.example.exceptions.UserUnauthorizedException;
import com.example.model.Author;
import com.example.security.SecurityService;
import com.example.services.AuthorService;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.QueryValue;
import io.micronaut.http.annotation.Status;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import javax.annotation.Nullable;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Optional;

@Controller("/authors")
public class AuthorController {

    private final AuthorService authorService;
    private final SecurityService securityService;

    public AuthorController(AuthorService authorService,
                            SecurityService securityService) {
        this.authorService = authorService;
        this.securityService = securityService;
    }

    @Operation(operationId = "createAuthor",
            summary = "Create a new author",
            description = "Create a new author.",
            requestBody = @RequestBody(content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = CreateAuthorRequest.class)
            )),
            responses = {
                    @ApiResponse(responseCode = "201", description = "Author created ok",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Author.class))
                    ),
                    @ApiResponse(responseCode = "400", description = "invalid or missing parameters"),
                    @ApiResponse(responseCode = "401", description = "if not authenticated as admin"),
            })
    @Post
    @Status(HttpStatus.CREATED)
    public Author createAuthor(@NotNull @Valid @Body CreateAuthorRequest createAuthorRequest) {
        return authorService.saveAuthor(createAuthorRequest.getName());
    }

    @Operation(operationId = "findAuthorByName",
            summary = "Find an author by name",
            description = "Find an author by name. Be aware that this is a special endpoint to explain how to create a 'Global Mock' in the tests, so for this to work the 'username' parameter needs to be 'admin'.",
            parameters = {
                    @Parameter(in = ParameterIn.QUERY, name = "author", required = true, description = "The author name to find", example = "Stephen King"),
                    @Parameter(in = ParameterIn.QUERY, name = "username", required = false, description = "The username", example = "admin"),
            },
            responses = {
                    @ApiResponse(responseCode = "200", description = "The author.",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Author.class))
                    ),
                    @ApiResponse(responseCode = "404", description = "if the author doesn't exist.")
            })
    @Get("/by-name")
    public Optional<Author> findAuthorByName(@NotBlank @QueryValue("author") String author,
                                             @QueryValue("username") @Nullable String username) {
        if (!securityService.canUserAccess(username)) {
            throw new UserUnauthorizedException();
        }

        // If the author is not found Micronaut will transform the empty `Optional` into a 404 response.
        return authorService.findAuthorByName(author);
    }
}
