package com.example.services;

import io.micronaut.core.annotation.Introspected;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Introspected
public class SaveBook {

    @NotBlank
    private final String title;

    @Min(1)
    private final int pages;

    @NotNull
    private final Long authorId;

    public SaveBook(@NotBlank String title,
                    @Min(1) int pages,
                    @NotNull Long authorId) {
        this.title = title;
        this.pages = pages;
        this.authorId = authorId;
    }

    public String getTitle() {
        return title;
    }

    public int getPages() {
        return pages;
    }

    public Long getAuthorId() {
        return authorId;
    }
}
