package com.ivanfranchin.moviesapi.model;

import com.ivanfranchin.moviesapi.rest.dto.CreateMovieRequest;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "movies")
public class Movie {

    @Id
    private String imdb;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private Short year;

    @Column(nullable = false)
    private Genre genre;

    @Column(nullable = false)
    private String country;

    public static Movie from(CreateMovieRequest createMovieRequest) {
        Movie movie = new Movie();
        movie.setImdb(createMovieRequest.imdb());
        movie.setTitle(createMovieRequest.title());
        movie.setYear(createMovieRequest.year());
        movie.setGenre(createMovieRequest.genre());
        movie.setCountry(createMovieRequest.country());
        return movie;
    }
}
