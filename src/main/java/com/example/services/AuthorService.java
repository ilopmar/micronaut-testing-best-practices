package com.example.services;

import com.example.model.Author;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Optional;

/**
 * Author service.
 */
public interface AuthorService {

    /**
     * Save an author without books.
     *
     * @param name The author name
     * @return The author
     */
    Author saveAuthor(@NotBlank String name);

    /**
     * Add a new book to an exiting author.
     *
     * @param saveBook The saveBook
     */
    void addBookToAuthor(@NotNull @Valid SaveBook saveBook);

    /**
     * Find an author by name.
     *
     * @param name The author name
     * @return An optional wrapping the author if exists
     */
    Optional<Author> findAuthorByName(@NotBlank String name);
}
