package com.ivanfranchin.moviesapi.movie.dto;

import com.ivanfranchin.moviesapi.movie.model.Genre;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record CreateMovieRequest(
        @NotBlank String imdb,
        @NotBlank String title,
        @Positive Short year,
        @NotNull Genre genre,
        @NotBlank String country) {
}
