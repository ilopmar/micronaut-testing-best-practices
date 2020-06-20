package com.example.controllers;

import io.micronaut.core.annotation.Introspected;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotBlank;

@Introspected
public class CreateAuthorRequest {

    @NotBlank
    @Schema(required = true, description = "The author name", example = "Stephen King")
    private final String name;

    public CreateAuthorRequest(@NotBlank String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
