package com.mycompany.moviesapi.rest.dto;

import com.mycompany.moviesapi.model.Genre;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
public class CreateMovieDto {

    @ApiModelProperty(example = "tt5580036")
    @NotBlank
    private String imdb;

    @ApiModelProperty(position = 1, example = "I, Tonya")
    @NotBlank
    private String title;

    @ApiModelProperty(position = 2, example = "2017")
    @Positive
    private Short year;

    @ApiModelProperty(position = 3, example = "Biography")
    @NotNull
    private Genre genre;

    @ApiModelProperty(position = 4, example = "USA")
    @NotBlank
    private String country;

}
