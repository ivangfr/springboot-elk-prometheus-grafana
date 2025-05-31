package com.ivanfranchin.moviesapi.rest.dto;

import com.ivanfranchin.moviesapi.model.Genre;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record CreateMovieRequest(
        @Schema(example = "tt5580036") @NotBlank String imdb,
        @Schema(example = "I, Tonya") @NotBlank String title,
        @Schema(example = "2017") @Positive Short year,
        @Schema(example = "Biography") @NotNull Genre genre,
        @Schema(example = "USA") @NotBlank String country) {
}
