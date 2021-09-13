package com.mycompany.moviesapi.service;

import com.mycompany.moviesapi.model.Movie;

import java.util.List;

public interface MovieService {

    Movie validateAndGetMovie(String imdb);

    List<Movie> getMovies();

    Movie createMovie(Movie movie);

    void deleteMovie(Movie movie);
}
