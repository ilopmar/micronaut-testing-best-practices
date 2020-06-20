package com.example.controllers

import com.example.EmbeddedServerSpecification
import com.example.fixtures.AuthorFixture
import com.example.model.Author
import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpResponse
import io.micronaut.http.HttpStatus
import io.micronaut.http.client.exceptions.HttpClientResponseException
import io.micronaut.http.uri.UriBuilder

class AuthorControllerFindAuthorWithSecuritySpec extends EmbeddedServerSpecification implements AuthorFixture {

    /*
      Same test as AuthorControllerFindAuthorSpec but in this case we disable the mock so this test uses the real
      security service implementation. Search the log for "Using REAL security service".
     */

    @Override
    boolean mockSecurityServiceEnabled() {
        false
    }

    void 'find an author by name fails because no admin user'() {
        given: 'an existing author'
        saveAuthor(authorName)

        and: 'an http request'
        URI uri = UriBuilder.of('/authors/by-name')
            .queryParam('author', authorName)
            .build()

        HttpRequest request = HttpRequest.GET(uri)

        when:
        client.exchange(request, Author)

        then:
        HttpClientResponseException e = thrown(HttpClientResponseException)
        e.status == HttpStatus.UNAUTHORIZED

        cleanup:
        authorRepository.deleteAll()

        where:
        authorName = 'My favourite author'
    }

    void 'find an author by name (with admin user)'() {
        given: 'an existing author'
        saveAuthor(authorName)

        and: 'an http request'
        URI uri = UriBuilder.of('/authors/by-name')
            .queryParam('author', authorName)
            // Pass the admin param so the "SecurityServiceImpl" returns true
            .queryParam('username', 'admin')
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

}
