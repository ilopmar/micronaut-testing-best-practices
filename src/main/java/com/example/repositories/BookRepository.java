package com.example.repositories;

import com.example.entities.BookEntity;
import com.example.model.Book;
import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.model.query.builder.sql.Dialect;
import io.micronaut.data.repository.CrudRepository;
import io.micronaut.validation.Validated;

import javax.validation.constraints.NotNull;

/**
 * Book repository for Postgres database.
 */
@Validated
@JdbcRepository(dialect = Dialect.POSTGRES)
public interface BookRepository extends CrudRepository<BookEntity, Long> {

    /**
     * Map a {@link BookEntity} to a {@link Book}.
     *
     * @param entity The book entity
     * @return The book
     */
    static Book dtoOfEntity(@NotNull BookEntity entity) {
        return new Book(entity.getTitle(), entity.getPages());
    }
}
