package com.example.controllers;

import io.micronaut.core.annotation.Introspected;

import javax.validation.constraints.NotBlank;

@Introspected
public class CreateAuthorRequest {

    @NotBlank
    private final String name;

    public CreateAuthorRequest(@NotBlank String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
