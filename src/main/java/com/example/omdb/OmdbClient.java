package com.example.omdb;

import io.micronaut.context.annotation.Value;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.QueryValue;
import io.micronaut.http.client.annotation.Client;

import javax.validation.constraints.NotBlank;
import java.util.Optional;

@Client("${omdb.base-url}")
public abstract class OmdbClient implements OmdbApi {

    @Value("${omdb.apikey}")
    String apikey;

    @Override
    public Optional<Movie> findMovieByTitle(@NotBlank String title) {
        Movie movie = this.findMovieByTitle(title, apikey);
        if (movie != null && movie.getTitle() != null) {
            return Optional.of(movie);
        } else {
            return Optional.empty();
        }
    }

    /**
     * This is the real method that the HTTP Client will implement. For implementing only this call I think it's fine
     * to define this method with the apikey and then the other method without it.
     *
     * @param title  The movie title to find
     * @param apikey The apikey
     * @return A movie
     */
    @Get("/")
    abstract Movie findMovieByTitle(@NotBlank @QueryValue("t") String title, @NotBlank @QueryValue("apikey") String apikey);
}
