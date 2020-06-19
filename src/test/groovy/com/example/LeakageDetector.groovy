package com.example

import com.example.fixtures.RepositoriesFixture

trait LeakageDetector extends RepositoriesFixture {

    boolean hasLeakage() {
        (
            bookRepository.count() > 0 ||
            authorRepository.count() > 0
        )
    }
}
