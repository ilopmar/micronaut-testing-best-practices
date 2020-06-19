package com.example.fixtures

import com.example.entities.AuthorEntity
import com.example.entities.BookEntity
import com.example.repositories.AuthorRepository
import com.example.repositories.BookRepository

trait AuthorFixture {

    abstract AuthorRepository getAuthorRepository()
    abstract BookRepository getBookRepository()

    AuthorEntity saveAuthor(String name = 'author name') {
        AuthorEntity author = new AuthorEntity(name)
        authorRepository.save(author)

        author
    }

    BookEntity saveBook(String title, AuthorEntity author, int pages = 1) {
        BookEntity bookEntity = new BookEntity(title, pages, author)
        bookRepository.save(bookEntity)

        bookEntity
    }
}
