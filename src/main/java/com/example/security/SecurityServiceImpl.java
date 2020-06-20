package com.example.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nullable;
import javax.inject.Singleton;

/**
 * Very naive security service.
 */
@Singleton
public class SecurityServiceImpl implements SecurityService {

    private static final Logger LOG = LoggerFactory.getLogger(SecurityServiceImpl.class);

    @Override
    public boolean canUserAccess(@Nullable String username) {
        LOG.debug(" ================ Using REAL security service ================ ");
        // Only admin can access
        return username != null && username.equals("admin");
    }
}
