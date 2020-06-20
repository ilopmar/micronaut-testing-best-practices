package com.example.services

import com.example.ApplicationContextSpecification
import spock.lang.Shared
import spock.lang.Subject
import spock.lang.Unroll

import javax.validation.ConstraintViolationException

class AuthorServiceImplConstraintsSpec extends ApplicationContextSpecification {

    @Subject
    @Shared
    AuthorService authorService = applicationContext.getBean(AuthorService)

    @Unroll
    void 'test "saveAuthor(#name)" triggers ConstraintViolationException'() {
        when:
        authorService.saveAuthor(name)

        then:
        def e = thrown(ConstraintViolationException)
        e.constraintViolations.collect { it.propertyPath.toString() }.any { it.contains(field) }
        e.constraintViolations.collect { it.message }.any { it.contains(errorMessage) }

        where:
        name | field             | errorMessage
        ''   | 'saveAuthor.name' | 'must not be blank'
        null | 'saveAuthor.name' | 'must not be blank'
    }

    @Unroll
    void 'test "addBookToAuthor(#saveBook)" triggers ConstraintViolationException'() {
        when:
        authorService.addBookToAuthor(saveBook)

        then:
        def e = thrown(ConstraintViolationException)
        e.constraintViolations.collect { it.propertyPath.toString() }.any { it.contains(field) }
        e.constraintViolations.collect { it.message }.any { it.contains(errorMessage) }

        where:
        saveBook | field                      | errorMessage
        null     | 'addBookToAuthor.saveBook' | 'must not be null'
    }

}
