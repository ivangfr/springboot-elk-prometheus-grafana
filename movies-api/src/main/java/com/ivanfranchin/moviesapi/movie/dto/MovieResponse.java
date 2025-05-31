package com.ivanfranchin.moviesapi.movie.dto;

import com.ivanfranchin.moviesapi.movie.model.Movie;

public record MovieResponse(String imdbId, String title, Short year, String genre, String country) {

    public static MovieResponse from(Movie movie) {
        String genre = null;
        if (movie.getGenre() != null) {
            genre = movie.getGenre().name();
        }
        return new MovieResponse(movie.getImdbId(), movie.getTitle(), movie.getYear(), genre, movie.getCountry());
    }
}
