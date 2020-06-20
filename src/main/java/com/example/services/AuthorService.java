package com.example.services;

import com.example.model.Author;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

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
}
