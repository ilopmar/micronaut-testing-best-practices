package com.example

import com.example.fixtures.ConfigurationFixture
import io.micronaut.context.ApplicationContext
import io.micronaut.core.io.socket.SocketUtils
import spock.lang.AutoCleanup
import spock.lang.Shared
import spock.lang.Specification

abstract class ApplicationContextSpecification extends Specification implements ConfigurationFixture, LeakageDetector {

    @Shared
    int omdbServerPort = SocketUtils.findAvailableTcpPort()

    @AutoCleanup
    @Shared
    ApplicationContext applicationContext = ApplicationContext.run(configuration)

    def cleanup() {
        assert !hasLeakage()
    }

}
