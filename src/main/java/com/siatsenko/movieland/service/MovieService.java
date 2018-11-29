package com.siatsenko.movieland.service;

import com.siatsenko.movieland.entity.Movie;

import java.util.List;

public interface MovieService {

    List<Movie> getAll(String order);

    List<Movie> getRandom();

    List<Movie> getByGenreId(int genreId, String order);
}
