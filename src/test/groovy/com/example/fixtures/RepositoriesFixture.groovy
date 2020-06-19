package com.example.fixtures

import com.example.repositories.AuthorRepository
import com.example.repositories.BookRepository
import io.micronaut.context.ApplicationContext

trait RepositoriesFixture {

    abstract ApplicationContext getApplicationContext()

    BookRepository getBookRepository() {
        applicationContext.getBean(BookRepository)
    }

    AuthorRepository getAuthorRepository() {
        applicationContext.getBean(AuthorRepository)
    }

}
