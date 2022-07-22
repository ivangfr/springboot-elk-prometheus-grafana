package com.ivanfranchin.moviesapi.service;

import com.ivanfranchin.moviesapi.model.Movie;

import java.util.List;

public interface MovieService {

    Movie validateAndGetMovie(String imdb);

    List<Movie> getMovies();

    Movie createMovie(Movie movie);

    void deleteMovie(Movie movie);
}
