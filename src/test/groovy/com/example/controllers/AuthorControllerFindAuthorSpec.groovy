package com.example.controllers

import com.example.EmbeddedServerSpecification
import com.example.fixtures.AuthorFixture
import com.example.model.Author
import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpResponse
import io.micronaut.http.HttpStatus
import io.micronaut.http.client.exceptions.HttpClientResponseException
import io.micronaut.http.uri.UriBuilder

class AuthorControllerFindAuthorSpec extends EmbeddedServerSpecification implements AuthorFixture {

    /*
      This test pass because by default we are using the MockSecurityService that always returns true.
      Run the test and search in the log for "Using mock security service"
     */

    void 'find an author by name'() {
        given: 'an existing author'
        saveAuthor(authorName)

        and: 'an http request'
        URI uri = UriBuilder.of('/authors/by-name')
            .queryParam('author', authorName)
            .build()

        HttpRequest request = HttpRequest.GET(uri)

        when:
        HttpResponse<Author> response = client.exchange(request, Author)

        then:
        response.status() == HttpStatus.OK

        when:
        Author author = response.body()

        then:
        author.name == authorName

        cleanup:
        authorRepository.deleteAll()

        where:
        authorName = 'My favourite author'
    }

    void 'try to find a user that does not exist returns 404'() {
        given: 'an http request'
        URI uri = UriBuilder.of('/authors/by-name')
            .queryParam('author', 'Iván')
            .build()
        HttpRequest request = HttpRequest.GET(uri)

        when:
        client.exchange(request, Author)

        then:
        HttpClientResponseException e = thrown(HttpClientResponseException)
        e.status == HttpStatus.NOT_FOUND
    }

}
