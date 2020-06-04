package com.mycompany.moviesapi.rest;

import com.mycompany.moviesapi.exception.MovieNotFoundException;
import com.mycompany.moviesapi.mapper.MovieMapper;
import com.mycompany.moviesapi.model.Movie;
import com.mycompany.moviesapi.rest.dto.CreateMovieDto;
import com.mycompany.moviesapi.service.MovieService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

import static net.logstash.logback.argument.StructuredArguments.v;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/movies")
public class MovieController {

    private final MovieService movieService;
    private final MovieMapper movieMapper;

    @GetMapping
    public List<Movie> getMovies() {
        List<Movie> movies = movieService.getMovies();
        log.info("Get all movies", v("numMoviesReturned", movies.size()));
        return movies;
    }

    @GetMapping("/{imdb}")
    public Movie getMovie(@PathVariable("imdb") String imdb) throws MovieNotFoundException {
        if (imdb.equals("111")) {
            throw new NullPointerException("It is know there is a bug with this movie");
        }
        log.info("Get movie", v("imdb", imdb));
        return movieService.validateAndGetMovie(imdb);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Movie createMovie(@Valid @RequestBody CreateMovieDto createMovieDto) {
        Movie movie = movieMapper.toMovie(createMovieDto);
        movie = movieService.createMovie(movie);
        log.info("Movie created", v("imdb", movie.getImdb()));
        return movie;
    }

    @DeleteMapping("/{imdb}")
    public String deleteMovie(@PathVariable("imdb") String imdb) throws MovieNotFoundException {
        Movie movie = movieService.validateAndGetMovie(imdb);
        movieService.deleteMovie(movie);
        log.info("Movie deleted", v("imdb", movie.getImdb()));
        return movie.getImdb();
    }

}
