package com.example.model;

import io.micronaut.core.annotation.Introspected;

/**
 * DTO for {@link com.example.entities.BookEntity}.
 */
@Introspected
public class Book {

    private final String title;
    private final int pages;

    public Book(String title, int pages) {
        this.title = title;
        this.pages = pages;
    }

    public String getTitle() {
        return title;
    }

    public int getPages() {
        return pages;
    }

    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", pages=" + pages +
                '}';
    }
}
