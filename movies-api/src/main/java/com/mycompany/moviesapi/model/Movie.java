package com.mycompany.moviesapi.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
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
