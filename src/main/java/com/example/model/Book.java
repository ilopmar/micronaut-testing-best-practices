package com.example.model;

import io.micronaut.core.annotation.Introspected;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * DTO for {@link com.example.entities.BookEntity}.
 */
@Introspected
public class Book {

    @Schema(required = true, description = "The book title", example = "Carrie")
    private final String title;

    @Schema(required = true, description = "The number of pages", example = "550")
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
