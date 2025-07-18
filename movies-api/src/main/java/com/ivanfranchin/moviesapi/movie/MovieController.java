package com.ivanfranchin.moviesapi.movie;

import com.ivanfranchin.moviesapi.movie.model.Movie;
import com.ivanfranchin.moviesapi.movie.dto.CreateMovieRequest;
import com.ivanfranchin.moviesapi.movie.dto.MovieResponse;
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

    @GetMapping("/{imdbId}")
    public MovieResponse getMovie(@PathVariable("imdbId") String imdbId) {
        log.debug("Get movie {}", kv("imdbId", imdbId));
        if (imdbId.equals("111")) {
            throw new NullPointerException("It's known that there is a bug with this movie");
        }
        Movie movie = movieService.validateAndGetMovie(imdbId);
        return MovieResponse.from(movie);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public MovieResponse createMovie(@Valid @RequestBody CreateMovieRequest createMovieRequest) {
        log.debug("Movie created {}", kv("imdbId", createMovieRequest.imdbId()));
        Movie movie = Movie.from(createMovieRequest);
        movie = movieService.createMovie(movie);
        return MovieResponse.from(movie);
    }

    @DeleteMapping("/{imdbId}")
    public String deleteMovie(@PathVariable("imdbId") String imdbId) {
        log.debug("Movie deleted {}", kv("imdbId", imdbId));
        Movie movie = movieService.validateAndGetMovie(imdbId);
        movieService.deleteMovie(movie);
        return movie.getImdbId();
    }
}
