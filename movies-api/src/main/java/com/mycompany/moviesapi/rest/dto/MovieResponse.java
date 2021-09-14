package com.mycompany.moviesapi.rest.dto;

import lombok.Value;

@Value
public class MovieResponse {

    String imdb;
    String title;
    Short year;
    String genre;
    String country;
}
