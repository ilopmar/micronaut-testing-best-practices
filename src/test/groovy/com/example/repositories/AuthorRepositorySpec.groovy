package com.example.repositories

import com.example.ApplicationContextSpecification
import com.example.entities.AuthorEntity

class AuthorRepositorySpec extends ApplicationContextSpecification {

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

}
