package com.ivanfranchin.moviesapi.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "movies")
public class Movie {

    @Id
    private String imdb;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private Short year;

    @Column(nullable = false)
    private Genre genre;

    @Column(nullable = false)
    private String country;
}
