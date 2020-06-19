package com.example.repositories;

import com.example.entities.AuthorEntity;
import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.model.query.builder.sql.Dialect;
import io.micronaut.data.repository.CrudRepository;
import io.micronaut.validation.Validated;

/**
 * Author repository for Postgres database.
 */
@Validated
@JdbcRepository(dialect = Dialect.POSTGRES)
public interface AuthorRepository extends CrudRepository<AuthorEntity, Long> {
}
