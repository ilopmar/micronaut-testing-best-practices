package com.example.model;

import io.micronaut.core.annotation.Introspected;

import java.util.List;

/**
 * DTO for {@link com.example.entities.AuthorEntity}.
 */
@Introspected
public class Author {

    private final Long id;
    private final String name;
    private final List<Book> books;

    public Author(Long id, String name, List<Book> books) {
        this.id = id;
        this.name = name;
        this.books = books;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Book> getBooks() {
        return books;
    }

    @Override
    public String toString() {
        return "Author{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", books=" + books +
                '}';
    }
}
