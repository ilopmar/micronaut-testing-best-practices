package com.example.services;

import com.example.entities.AuthorEntity;
import com.example.entities.BookEntity;
import com.example.model.Author;
import com.example.repositories.AuthorRepository;
import com.example.repositories.BookRepository;

import javax.inject.Singleton;
import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Optional;

@Singleton
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository,
                             BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    @Transactional
    public Author saveAuthor(@NotBlank String name) {
        AuthorEntity authorEntity = authorRepository.save(new AuthorEntity(name));
        // TODO: Error handling
        return AuthorRepository.dtoOfEntity(authorEntity);
    }

    @Override
    @Transactional
    public void addBookToAuthor(@NotNull @Valid SaveBook saveBook) {
        Optional<AuthorEntity> optAuthor = authorRepository.findById(saveBook.getAuthorId());

        if (optAuthor.isEmpty()) {
            // TODO: Error handling (exercise for the reader).
            //  Throw an exception and create an exception handler to return 404.
            return;
        }

        AuthorEntity author = optAuthor.get();
        BookEntity bookEntity = new BookEntity(saveBook.getTitle(), saveBook.getPages(), author);
        bookRepository.save(bookEntity);
    }
}
