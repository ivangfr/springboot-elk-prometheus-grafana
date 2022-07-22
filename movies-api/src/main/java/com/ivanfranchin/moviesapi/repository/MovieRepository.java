package com.ivanfranchin.moviesapi.repository;

import com.ivanfranchin.moviesapi.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends JpaRepository<Movie, String> {
}
