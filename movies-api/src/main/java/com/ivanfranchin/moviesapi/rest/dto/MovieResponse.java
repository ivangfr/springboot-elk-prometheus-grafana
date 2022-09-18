package com.ivanfranchin.moviesapi.rest.dto;

public record MovieResponse(String imdb, String title, Short year, String genre, String country) {
}
