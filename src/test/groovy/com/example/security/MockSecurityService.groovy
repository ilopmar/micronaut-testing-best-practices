package com.example.security

import io.micronaut.context.annotation.Primary
import io.micronaut.context.annotation.Requires
import io.micronaut.context.env.Environment
import io.micronaut.core.util.StringUtils
import org.slf4j.Logger
import org.slf4j.LoggerFactory

import javax.annotation.Nullable
import javax.inject.Singleton

@Primary
@Singleton
@Requires(env = Environment.TEST)
@Requires(property = 'mockSecurityService', value = StringUtils.TRUE)
class MockSecurityService implements SecurityService {

    private static final Logger LOG = LoggerFactory.getLogger(MockSecurityService.class)

    @Override
    boolean canUserAccess(@Nullable String username) {
        LOG.debug(' ================ Using mock security service ================ ')
        true
    }
}
