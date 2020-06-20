package com.example.services

import com.example.ApplicationContextSpecification
import com.example.entities.AuthorEntity
import com.example.fixtures.AuthorFixture
import com.example.fixtures.BookFixture
import com.example.model.Author
import spock.lang.Shared
import spock.lang.Subject

class AuthorServiceSpec extends ApplicationContextSpecification implements AuthorFixture, BookFixture {

    @Subject
    @Shared
    AuthorService authorService = applicationContext.getBean(AuthorService)

    void 'save an author'() {
        when: 'saving an author'
        Author author = authorService.saveAuthor(name)

        then: 'it is saved'
        noExceptionThrown()
        author.id
        author.name == name

        cleanup:
        authorRepository.deleteAll()

        where:
        name = 'Stephen King'
    }

    void 'add a book to an existing author'() {
        given: 'an author'
        AuthorEntity authorEntity = saveAuthor()

        and: 'a saveBook object'
        SaveBook saveBook = createSaveBook(authorEntity.id)

        when: 'adding the book to the author'
        authorService.addBookToAuthor(saveBook)

        then: 'there is no exception'
        noExceptionThrown()

        and: 'the book is assigned to the author'
        Author author = authorRepository.findAuthorByName(authorEntity.name).get()
        author.books.size() == 1
        author.books.first().title == saveBook.title
        author.books.first().pages == saveBook.pages

        cleanup:
        bookRepository.deleteAll()
        authorRepository.deleteAll()
    }
}
