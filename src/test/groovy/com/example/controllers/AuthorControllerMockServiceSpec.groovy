package com.example.controllers

import com.example.EmbeddedServerSpecification
import com.example.fixtures.AuthorFixture
import com.example.model.Author
import com.example.services.AuthorService
import com.example.services.SaveBook
import io.micronaut.context.annotation.Primary
import io.micronaut.context.annotation.Requires
import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpStatus
import io.micronaut.http.client.exceptions.HttpClientResponseException

import javax.inject.Singleton
import javax.validation.Valid
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

class AuthorControllerMockServiceSpec extends EmbeddedServerSpecification implements AuthorFixture {

    /*
     In this test we're going to simulate that there is an error when saving the author:
       - The AuthorServiceMock bean we define in this class will only be used for this test because of the @Requires
         annotation and it throws an exception (in a real app you will use here your own exception and you will also
         have an exception handler created for it. See https://docs.micronaut.io/snapshot/guide/index.html#_exceptionhandler
       - In the test we only check that the server returns a 500 because the previous exception is thrown
       - We don't need to check that the author has not been created because if, for some reason, the mock is not working
         then the "hasLeakage" will detect that we are not cleaning up the database and the test would fail.-
     */

    @Override
    String getSpecName() {
        'AuthorControllerMockServiceSpec'
    }

    void 'try create an author but service fails'() {
        given: 'a create author request'
        CreateAuthorRequest createAuthorRequest = createAuthorRequest()

        and: 'the http request'
        HttpRequest request = HttpRequest.POST('/authors', createAuthorRequest)

        when: 'sending the http request'
        client.exchange(request, Author)

        then: 'there was an error saving the author'
        HttpClientResponseException e = thrown(HttpClientResponseException)
        e.status == HttpStatus.INTERNAL_SERVER_ERROR
    }

    @Singleton
    @Primary
    @Requires(property = 'spec.name', value = 'AuthorControllerMockServiceSpec')
    static class AuthorServiceMock implements AuthorService {
        @Override
        Author saveAuthor(@NotBlank String name) {
            throw new RuntimeException("There was an error saving the author.")
        }

        @Override
        void addBookToAuthor(@NotNull @Valid SaveBook saveBook) {
            // not used in this test
        }

        @Override
        Optional<Author> findAuthorByName(@NotBlank String name) {
            // not used in this test
            Optional.empty()
        }
    }

}
