package com.example.controllers

import com.example.OmdbEmbeddedServerSpecification
import com.example.omdb.Movie
import groovy.util.logging.Slf4j
import io.micronaut.context.annotation.Requires
import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpResponse
import io.micronaut.http.HttpStatus
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.QueryValue
import io.micronaut.http.uri.UriBuilder

@Slf4j
class MovieControllerSpec extends OmdbEmbeddedServerSpecification {

    @Override
    String getOmdbSpecName() {
        'MovieControllerSpec'
    }

    void 'find a movie by title'() {
        given: 'an http request'
        URI uri = UriBuilder.of('/movies/by-title')
            .queryParam('title', 'it does not really matter')
            .build()

        HttpRequest request = HttpRequest.GET(uri)

        when:
        HttpResponse<Movie> response = client.exchange(request, Movie)

        then:
        response.status() == HttpStatus.OK

        when:
        Movie movie = response.body()

        then:
        movie.title == 'Star Wars: Episode IV - A New Hope'
        movie.year == '1977'
    }

    @Controller("/")
    @Requires(property = 'spec.name', value = 'MovieControllerSpec')
    static class OmbdMock {

        @Get
        String findMovie(@QueryValue("t") String title) {
            log.debug " ============== ${title} ============== "
            // This is the real response from by the api we just return it as a String because Micronaut will do the marshalling to out Movie POJO.
            '{"Title":"Star Wars: Episode IV - A New Hope","Year":"1977","Rated":"PG","Released":"25 May 1977","Runtime":"121 min","Genre":"Action, Adventure, Fantasy, Sci-Fi","Director":"George Lucas","Writer":"George Lucas","Actors":"Mark Hamill, Harrison Ford, Carrie Fisher, Peter Cushing","Plot":"Luke Skywalker joins forces with a Jedi Knight, a cocky pilot, a Wookiee and two droids to save the galaxy from the Empire\'s world-destroying battle station, while also attempting to rescue Princess Leia from the mysterious Darth Vader.","Language":"English","Country":"USA","Awards":"Won 6 Oscars. Another 52 wins & 28 nominations.","Poster":"https://m.media-amazon.com/images/M/MV5BNzVlY2MwMjktM2E4OS00Y2Y3LWE3ZjctYzhkZGM3YzA1ZWM2XkEyXkFqcGdeQXVyNzkwMjQ5NzM@._V1_SX300.jpg","Ratings":[{"Source":"Internet Movie Database","Value":"8.6/10"},{"Source":"Rotten Tomatoes","Value":"92%"},{"Source":"Metacritic","Value":"90/100"}],"Metascore":"90","imdbRating":"8.6","imdbVotes":"1,194,693","imdbID":"tt0076759","Type":"movie","DVD":"21 Sep 2004","BoxOffice":"N/A","Production":"20th Century Fox","Website":"N/A","Response":"True"}'
        }

    }

}
