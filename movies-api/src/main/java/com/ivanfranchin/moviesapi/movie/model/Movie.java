package com.ivanfranchin.moviesapi.movie.model;

import com.ivanfranchin.moviesapi.movie.dto.CreateMovieRequest;
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
    private String imdbId;

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
        movie.setImdbId(createMovieRequest.imdbId());
        movie.setTitle(createMovieRequest.title());
        movie.setYear(createMovieRequest.year());
        movie.setGenre(createMovieRequest.genre());
        movie.setCountry(createMovieRequest.country());
        return movie;
    }
}
