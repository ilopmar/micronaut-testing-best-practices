package com.example.repositories;

import com.example.entities.AuthorEntity;
import com.example.model.Author;
import io.micronaut.data.annotation.Join;
import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.model.query.builder.sql.Dialect;
import io.micronaut.data.repository.CrudRepository;
import io.micronaut.validation.Validated;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Author repository for Postgres database.
 */
@Validated
@JdbcRepository(dialect = Dialect.POSTGRES)
public interface AuthorRepository extends CrudRepository<AuthorEntity, Long> {

    /**
     * Find an author by name.
     *
     * @param name The author name
     * @return An optional wrapping the author with their books
     */
    default Optional<Author> findAuthorByName(@NotBlank String name) {
        return this.findByName(name).map(AuthorRepository::dtoOfEntity);
    }

    @Join(value = "books", type = Join.Type.LEFT_FETCH)
    Optional<AuthorEntity> findByName(@NotBlank String name);

    /**
     * Map an {@link AuthorEntity} to a {@link Author}.
     *
     * @param entity The author entity
     * @return The author with the books
     */
    static Author dtoOfEntity(@NotNull AuthorEntity entity) {
        return new Author(
                entity.getId(),
                entity.getName(),
                entity.getBooks().stream()
                        .map(BookRepository::dtoOfEntity)
                        .collect(Collectors.toList())
        );
    }

}
