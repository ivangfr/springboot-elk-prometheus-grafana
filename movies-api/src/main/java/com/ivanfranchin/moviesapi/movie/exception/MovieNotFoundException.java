package com.ivanfranchin.moviesapi.movie.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class MovieNotFoundException extends RuntimeException {

    public MovieNotFoundException(String imdb) {
        super(String.format("Movie with imdb %s not found", imdb));
    }
}
