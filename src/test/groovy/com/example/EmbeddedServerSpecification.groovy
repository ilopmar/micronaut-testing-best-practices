package com.example

import com.example.fixtures.ConfigurationFixture
import io.micronaut.context.ApplicationContext
import io.micronaut.http.client.BlockingHttpClient
import io.micronaut.http.client.HttpClient
import io.micronaut.runtime.server.EmbeddedServer
import spock.lang.AutoCleanup
import spock.lang.Shared
import spock.lang.Specification

abstract class EmbeddedServerSpecification extends Specification implements ConfigurationFixture, LeakageDetector {

    @AutoCleanup
    @Shared
    EmbeddedServer embeddedServer = ApplicationContext.run(EmbeddedServer, configuration)

    @AutoCleanup
    @Shared
    ApplicationContext applicationContext = embeddedServer.applicationContext

    @AutoCleanup
    @Shared
    HttpClient httpClient = applicationContext.createBean(HttpClient, embeddedServer.URL)

    BlockingHttpClient getClient() {
        httpClient.toBlocking()
    }

    def cleanup() {
        assert !hasLeakage()
    }

}
