package com.mycompany.moviesapi.mapper;

import com.mycompany.moviesapi.model.Movie;
import com.mycompany.moviesapi.rest.dto.CreateMovieRequest;
import com.mycompany.moviesapi.rest.dto.MovieResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MovieMapper {

    Movie toMovie(CreateMovieRequest createMovieRequest);

    MovieResponse toMovieResponse(Movie movie);
}
