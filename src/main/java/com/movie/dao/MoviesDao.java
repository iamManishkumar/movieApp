package com.movie.dao;

import com.movie.model.Movies;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MoviesDao extends JpaRepository<Movies,Integer> {

}
