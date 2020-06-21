package com.example.services;

import com.example.model.Author;
import com.example.repositories.AuthorRepository;
import io.micronaut.context.annotation.Requires;
import io.micronaut.context.env.Environment;
import io.micronaut.context.event.StartupEvent;
import io.micronaut.runtime.event.annotation.EventListener;

import javax.inject.Singleton;

@Singleton
@Requires(notEnv = Environment.TEST)
public class BootstrapService {

    private final AuthorService authorService;
    private final AuthorRepository authorRepository;

    public BootstrapService(AuthorService authorService,
                       AuthorRepository authorRepository) {
        this.authorService = authorService;
        this.authorRepository = authorRepository;
    }

    @EventListener
    void init(StartupEvent event) {
        if (authorRepository.count() == 0) {
            Author stephenKing = authorService.saveAuthor("Stephen King");
            authorService.addBookToAuthor(new SaveBook("Carrie", 199, stephenKing.getId()));
            authorService.addBookToAuthor(new SaveBook("The Stand", 823, stephenKing.getId()));
        }
    }
}
