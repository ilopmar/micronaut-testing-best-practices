package com.example.repositories

import com.example.ApplicationContextSpecification
import com.example.entities.AuthorEntity
import com.example.entities.BookEntity

class BookRepositorySpec extends ApplicationContextSpecification {

    void 'save a book'() {
        given: 'an author'
        AuthorEntity author = new AuthorEntity('Stephen King')
        authorRepository.save(author)

        and: 'a book'
        BookEntity book = new BookEntity('The Stand', 550, author)

        when: 'saving it'
        bookRepository.save(book)

        then: 'it is saved'
        noExceptionThrown()
        book.id
        book.dateCreated

        cleanup:
        bookRepository.deleteAll()
        authorRepository.deleteAll()
    }

}
