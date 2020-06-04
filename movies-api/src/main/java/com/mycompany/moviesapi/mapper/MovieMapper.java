package com.mycompany.moviesapi.mapper;

import com.mycompany.moviesapi.model.Movie;
import com.mycompany.moviesapi.rest.dto.CreateMovieDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MovieMapper {

    Movie toMovie(CreateMovieDto createMovieDto);

}
