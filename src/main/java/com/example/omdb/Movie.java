package com.example.omdb;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.micronaut.core.annotation.Introspected;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * OMDB Movie.
 *
 * See http://www.omdbapi.com/#examples
 */
@Introspected
@JsonNaming(PropertyNamingStrategy.UpperCamelCaseStrategy.class)
public class Movie {

    // Only interested in these two fields
    @Schema(required = true, description = "The movie title", example = "Carrie")
    private String title;

    @Schema(required = true, description = "The movie year", example = "1977")
    private String year;

    /*
{
    "Title": "Carrie",
    "Year": "1976",
    "Rated": "R",
    "Released": "16 Nov 1976",
    "Runtime": "98 min",
    "Genre": "Horror",
    "Director": "Brian De Palma",
    "Writer": "Stephen King (novel), Lawrence D. Cohen (screenplay)",
    "Actors": "Sissy Spacek, Piper Laurie, Amy Irving, William Katt",
    "Plot": "Carrie White, a shy, friendless teenage girl who is sheltered by her domineering, religious mother, unleashes her telekinetic powers after being humiliated by her classmates at her senior prom.",
    "Language": "English",
    "Country": "USA",
    "Awards": "Nominated for 2 Oscars. Another 3 wins & 5 nominations.",
    "Poster": "https://m.media-amazon.com/images/M/MV5BMTlhNmVkZGUtNjdjOC00YWY3LTljZWQtMTY1YWFhNGYwNDQwXkEyXkFqcGdeQXVyNjc1NTYyMjg@._V1_SX300.jpg",
    "Ratings": [{
            "Source": "Internet Movie Database",
            "Value": "7.4/10"
        },
        {
            "Source": "Rotten Tomatoes",
            "Value": "92%"
        },
        {
            "Source": "Metacritic",
            "Value": "85/100"
        }
    ],
    "Metascore": "85",
    "imdbRating": "7.4",
    "imdbVotes": "161,967",
    "imdbID": "tt0074285",
    "Type": "movie",
    "DVD": "29 Sep 1998",
    "BoxOffice": "N/A",
    "Production": "United Artists",
    "Website": "N/A",
    "Response": "True"
}
     */

    public Movie() {
    }

    public Movie(String title, String year) {
        this.title = title;
        this.year = year;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "title='" + title + '\'' +
                ", year='" + year + '\'' +
                '}';
    }
}
