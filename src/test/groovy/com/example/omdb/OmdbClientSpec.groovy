package com.example.omdb

import com.example.ApplicationContextSpecification
import spock.lang.Ignore
import spock.lang.Shared
import spock.lang.Subject

@Ignore
class OmdbClientSpec extends ApplicationContextSpecification {

    /*
     This test will fail unless you set a valid OMDB apikey in `application.yml`
     See http://www.omdbapi.com/ to generate it. It's free.

     Also keep in mind that this test sends a real HTTP call to the 3rd party API.

     To run the test remove the @Ignore annotation.
     */

    @Shared
    @Subject
    OmdbClient omdbClient = applicationContext.getBean(OmdbClient)

    void 'find a movie on OMDB'() {
        when:
        Optional<Movie> optMovie = omdbClient.findMovieByTitle('carrie')

        then:
        optMovie.isPresent()

        when:
        Movie movie = optMovie.get()

        then:
        movie.title.toLowerCase() == 'carrie'
        movie.year
    }

    void 'try to find a movie on OMDB that does not exist'() {
        when:
        Optional<Movie> optMovie = omdbClient.findMovieByTitle('xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx')

        then:
        optMovie.isEmpty()
    }
}
