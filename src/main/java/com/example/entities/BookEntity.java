package com.example.entities;

import io.micronaut.data.annotation.DateCreated;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * Book entity.
 */
@Entity
@Table(name = "book")
public class BookEntity {

    @Id
    @GeneratedValue
    private Long id;

    @NotBlank
    private String title;

    @Min(1)
    private int pages;

    @DateCreated
    private LocalDateTime dateCreated;

    @NotNull
    @OneToOne
    private AuthorEntity author;

    public BookEntity(@NotBlank String title,
                      @Min(1) int pages,
                      @NotNull AuthorEntity author) {
        this.title = title;
        this.pages = pages;
        this.author = author;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public LocalDateTime getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDateTime dateCreated) {
        this.dateCreated = dateCreated;
    }

    public AuthorEntity getAuthor() {
        return author;
    }

    public void setAuthor(AuthorEntity author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "BookEntity{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", pages=" + pages +
                ", dateCreated=" + dateCreated +
                ", author=" + author +
                '}';
    }
}
