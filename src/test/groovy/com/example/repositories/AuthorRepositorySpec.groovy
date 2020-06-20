package com.example.repositories

import com.example.ApplicationContextSpecification
import com.example.entities.AuthorEntity
import com.example.fixtures.AuthorFixture
import com.example.fixtures.BookFixture
import com.example.model.Author

class AuthorRepositorySpec extends ApplicationContextSpecification implements AuthorFixture, BookFixture {

    void 'save an author'() {
        given: 'an author'
        AuthorEntity author = new AuthorEntity('Stephen King')

        when: 'saving it'
        authorRepository.save(author)

        then: 'it is saved'
        noExceptionThrown()
        author.id
        author.dateCreated

        cleanup:
        authorRepository.deleteAll()
    }

    void 'find author by name with their books'() {
        given: 'an author'
        AuthorEntity authorEntity = saveAuthor(name)

        and: 'some books related'
        saveBook('book1', authorEntity)
        saveBook('book2', authorEntity)
        saveBook('book3', authorEntity)

        when: 'finding the author by name'
        Optional<Author> optAuthor = authorRepository.findAuthorByName(name)

        then: 'it is found'
        optAuthor.isPresent()

        and: 'the author is retrieved with their books'
        Author author = optAuthor.get()
        author.id == authorEntity.id
        author.name == name
        author.books.size() == 3

        cleanup:
        authorRepository.deleteAll()
        bookRepository.deleteAll()

        where:
        name = 'Stephen King'
    }

}
