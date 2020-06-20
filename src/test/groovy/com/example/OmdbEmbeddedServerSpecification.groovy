package com.example

import io.micronaut.context.ApplicationContext
import io.micronaut.runtime.server.EmbeddedServer
import spock.lang.AutoCleanup
import spock.lang.Shared

abstract class OmdbEmbeddedServerSpecification extends EmbeddedServerSpecification {

    @AutoCleanup
    @Shared
    EmbeddedServer omdbEmbeddedServer = ApplicationContext.run(EmbeddedServer, configuration + [
        'micronaut.server.port': omdbServerPort,
        'spec.name'            : omdbSpecName,
    ])
}
