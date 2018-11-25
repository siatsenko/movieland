package com.siatsenko.movieland.dao;

import com.siatsenko.movieland.entity.Movie;

import java.util.List;

public interface MovieDao {

    List<Movie> getAll();

    List<Movie> getRandom();

}
