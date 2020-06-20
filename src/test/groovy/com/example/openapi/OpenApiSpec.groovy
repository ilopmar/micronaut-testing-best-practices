package com.example.openapi

import com.example.EmbeddedServerSpecification
import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpResponse
import io.micronaut.http.HttpStatus

class OpenApiSpec extends EmbeddedServerSpecification {

    void 'OpenAPI yml file is exposed'() {
        when:
        HttpResponse response = client.exchange(HttpRequest.GET('/swagger/demo-0.1.yml'))

        then:
        noExceptionThrown()
        response.status() == HttpStatus.OK
    }
}
