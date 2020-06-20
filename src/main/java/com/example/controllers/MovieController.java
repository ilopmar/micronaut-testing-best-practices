package com.example.controllers;

import com.example.omdb.Movie;
import com.example.omdb.OmdbClient;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.QueryValue;

import javax.validation.constraints.NotBlank;

@Controller("/movies")
public class MovieController {

    private final OmdbClient omdbClient;

    public MovieController(OmdbClient omdbClient) {
        this.omdbClient = omdbClient;
    }

    @Get("/by-title")
    public Movie findMovieByTitle(@NotBlank @QueryValue("title") String title) {
        return omdbClient.findMovieByTitle(title)
                .orElse(null);
    }
}
