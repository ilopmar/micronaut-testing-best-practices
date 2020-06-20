package com.example.security;

import io.micronaut.context.annotation.DefaultImplementation;

import javax.annotation.Nullable;

/**
 * Security service
 */
@DefaultImplementation(SecurityServiceImpl.class)
public interface SecurityService {

    /**
     * Whether a user can access some restricted feature.
     *
     * @param username The username
     * @return Whether a user can access or not
     */
    boolean canUserAccess(@Nullable String username);
}
