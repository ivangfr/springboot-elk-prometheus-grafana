package com.ivanfranchin.moviesapi.rest;

import com.ivanfranchin.moviesapi.model.Movie;
import com.ivanfranchin.moviesapi.rest.dto.CreateMovieRequest;
import com.ivanfranchin.moviesapi.rest.dto.MovieResponse;
import com.ivanfranchin.moviesapi.service.MovieService;
import jakarta.validation.Valid;
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

import java.util.List;

import static net.logstash.logback.argument.StructuredArguments.kv;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/movies")
public class MovieController {

    private final MovieService movieService;

    @GetMapping
    public List<MovieResponse> getMovies() {
        log.debug("Get all movies");
        return movieService.getMovies().stream()
                .map(MovieResponse::from)
                .toList();
    }

    @GetMapping("/{imdb}")
    public MovieResponse getMovie(@PathVariable("imdb") String imdb) {
        log.debug("Get movie {}", kv("imdb", imdb));
        if (imdb.equals("111")) {
            throw new NullPointerException("It's known that there is a bug with this movie");
        }
        Movie movie = movieService.validateAndGetMovie(imdb);
        return MovieResponse.from(movie);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public MovieResponse createMovie(@Valid @RequestBody CreateMovieRequest createMovieRequest) {
        log.debug("Movie created {}", kv("imdb", createMovieRequest.imdb()));
        Movie movie = Movie.from(createMovieRequest);
        movie = movieService.createMovie(movie);
        return MovieResponse.from(movie);
    }

    @DeleteMapping("/{imdb}")
    public String deleteMovie(@PathVariable("imdb") String imdb) {
        log.debug("Movie deleted {}", kv("imdb", imdb));
        Movie movie = movieService.validateAndGetMovie(imdb);
        movieService.deleteMovie(movie);
        return movie.getImdb();
    }
}
