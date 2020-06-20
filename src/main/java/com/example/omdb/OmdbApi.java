package com.example.omdb;

import javax.validation.constraints.NotBlank;
import java.util.Optional;

/**
 * Find a movie by title.
 */
public interface OmdbApi {

    /**
     * Find a movie by title.
     *
     * @param title The movie title
     * @return An optional wrapping the movie
     */
    Optional<Movie> findMovieByTitle(@NotBlank String title);
}
