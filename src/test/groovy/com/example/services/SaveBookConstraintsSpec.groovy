package com.example.services

import com.example.ApplicationContextSpecification
import io.micronaut.validation.validator.Validator
import spock.lang.Shared
import spock.lang.Unroll

import javax.validation.ConstraintViolation

class SaveBookConstraintsSpec extends ApplicationContextSpecification {

    @Shared
    Validator validator = applicationContext.getBean(Validator)

    void 'a saveBook with all valid parameters does not generate validation errors'() {
        given:
        SaveBook saveBook = new SaveBook('Carrie', 199, 1)

        when:
        Set<ConstraintViolation<SaveBook>> constraintViolations = validator.validate(saveBook)

        then:
        constraintViolations.isEmpty()
    }

    @Unroll
    void 'test validation errors for field #field'() {
        given:
        SaveBook saveBook = new SaveBook(title, pages, authorId)

        when:
        Set<ConstraintViolation<SaveBook>> constraintViolations = validator.validate(saveBook)

        then:
        constraintViolations.size() == 1
        constraintViolations.collect { it.propertyPath.toString() }.any { it.contains(field) }
        constraintViolations.collect { it.message }.any { it.contains(errorMessage) }

        where:
        title   | pages | authorId | field      | errorMessage
        ''      | 199   | 1        | 'title'    | 'must not be blank'
        null    | 199   | 1        | 'title'    | 'must not be blank'
        'title' | 0     | 1        | 'pages'    | 'must be greater than or equal to 1'
        'title' | 1     | null     | 'authorId' | 'must not be null'
    }
}
