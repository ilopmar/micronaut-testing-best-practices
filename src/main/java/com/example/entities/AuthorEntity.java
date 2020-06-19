package com.example.entities;

import io.micronaut.data.annotation.DateCreated;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

/**
 * Author entity.
 */
@Entity
@Table(name = "author")
public class AuthorEntity {

    @Id
    @GeneratedValue
    private Long id;

    @NotBlank
    private String name;

    @DateCreated
    private LocalDateTime dateCreated;

    @OneToMany(mappedBy = "author")
    private Set<BookEntity> books = new HashSet<>();

    public AuthorEntity(@NotBlank String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDateTime dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Set<BookEntity> getBooks() {
        return books;
    }

    public void setBooks(Set<BookEntity> books) {
        this.books = books;
    }

    @Override
    public String toString() {
        return "AuthorEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", dateCreated=" + dateCreated +
                ", books=" + books +
                '}';
    }
}
