package com.example.fixtures

import com.example.entities.AuthorEntity
import com.example.repositories.AuthorRepository

trait AuthorFixture {

    abstract AuthorRepository getAuthorRepository()

    AuthorEntity saveAuthor(String name = 'author name') {
        AuthorEntity author = new AuthorEntity(name)
        authorRepository.save(author)

        author
    }
}
