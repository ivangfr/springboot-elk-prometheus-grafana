package com.ivanfranchin.moviesapi.mapper;

import com.ivanfranchin.moviesapi.model.Movie;
import com.ivanfranchin.moviesapi.rest.dto.CreateMovieRequest;
import com.ivanfranchin.moviesapi.rest.dto.MovieResponse;
import org.springframework.stereotype.Service;

@Service
public class MovieMapperImpl implements MovieMapper {

    @Override
    public Movie toMovie(CreateMovieRequest createMovieRequest) {
        if (createMovieRequest == null) {
            return null;
        }
        Movie movie = new Movie();
        movie.setImdb(createMovieRequest.getImdb());
        movie.setTitle(createMovieRequest.getTitle());
        movie.setYear(createMovieRequest.getYear());
        movie.setGenre(createMovieRequest.getGenre());
        movie.setCountry(createMovieRequest.getCountry());
        return movie;
    }

    @Override
    public MovieResponse toMovieResponse(Movie movie) {
        if (movie == null) {
            return null;
        }
        String genre = null;
        if (movie.getGenre() != null) {
            genre = movie.getGenre().name();
        }
        return new MovieResponse(movie.getImdb(), movie.getTitle(), movie.getYear(), genre, movie.getCountry());
    }
}
