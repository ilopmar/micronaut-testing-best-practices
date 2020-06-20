package com.example.controllers;

import com.example.omdb.Movie;
import com.example.omdb.OmdbClient;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.QueryValue;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import javax.validation.constraints.NotBlank;

@Controller("/movies")
public class MovieController {

    private final OmdbClient omdbClient;

    public MovieController(OmdbClient omdbClient) {
        this.omdbClient = omdbClient;
    }

    @Operation(operationId = "findMovieByTitle",
            summary = "Find a movie by title",
            description = "Find a movie by title using an external API (OMBD).",
            responses = {
                    @ApiResponse(responseCode = "200", description = "The movie.",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Movie.class))
                    ),
                    @ApiResponse(responseCode = "404", description = "if the movie doesn't exist.")
            })
    @Get("/by-title")
    public Movie findMovieByTitle(@NotBlank @QueryValue("title") String title) {
        return omdbClient.findMovieByTitle(title)
                .orElse(null);
    }
}
