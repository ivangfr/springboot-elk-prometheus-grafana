package com.ivanfranchin.moviesapi.rest.dto;

import com.ivanfranchin.moviesapi.model.Genre;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class CreateMovieRequest {

    @Schema(example = "tt5580036")
    @NotBlank
    private String imdb;

    @Schema(example = "I, Tonya")
    @NotBlank
    private String title;

    @Schema(example = "2017")
    @Positive
    private Short year;

    @Schema(example = "Biography")
    @NotNull
    private Genre genre;

    @Schema(example = "USA")
    @NotBlank
    private String country;
}
