package com.ivanfranchin.moviesapi.mapper;

import com.ivanfranchin.moviesapi.model.Movie;
import com.ivanfranchin.moviesapi.rest.dto.CreateMovieRequest;
import com.ivanfranchin.moviesapi.rest.dto.MovieResponse;

public interface MovieMapper {

    Movie toMovie(CreateMovieRequest createMovieRequest);

    MovieResponse toMovieResponse(Movie movie);
}
