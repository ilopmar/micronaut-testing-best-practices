package com.example.controllers

import com.example.EmbeddedServerSpecification
import com.example.fixtures.AuthorFixture
import com.example.model.Author
import io.micronaut.core.type.Argument
import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpResponse
import io.micronaut.http.HttpStatus
import io.micronaut.http.client.exceptions.HttpClientResponseException

class AuthorControllerSpec extends EmbeddedServerSpecification implements AuthorFixture {

    void 'create an author (POST /authors) returns 201'() {
        given: 'a create author request'
        CreateAuthorRequest createAuthorRequest = createAuthorRequest()

        and: 'the http request'
        HttpRequest request = HttpRequest.POST('/authors', createAuthorRequest)

        when: 'sending the http request'
        HttpResponse<Author> response = client.exchange(request, Author)

        then: 'the shipment is created'
        response.status() == HttpStatus.CREATED

        when: 'getting the body of the response'
        Author author = response.body()

        then:
        author
        author.id
        author.name == createAuthorRequest.name
        author.books.isEmpty()

        cleanup:
        authorRepository.deleteAll()
    }

    void 'try to create a new author without mandatory params'() {
        given: 'an invalid create author request'
        CreateAuthorRequest createAuthorRequest = createAuthorRequest(null)

        and: 'the http request'
        HttpRequest request = HttpRequest.POST('/authors', createAuthorRequest)

        when: 'sending the http request'
        client.exchange(request, Argument.of(Author), Argument.of(Map))

        then: 'validation error'
        HttpClientResponseException e = thrown(HttpClientResponseException)
        e.status == HttpStatus.BAD_REQUEST
        Map error = e.response.getBody(Map).get()
        error.message == 'createAuthorRequest.name: must not be blank'
    }

}
