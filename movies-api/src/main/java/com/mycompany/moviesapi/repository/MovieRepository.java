package com.mycompany.moviesapi.repository;

import com.mycompany.moviesapi.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, String> {
}
