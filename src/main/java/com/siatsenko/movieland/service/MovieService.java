package com.siatsenko.movieland.service;

import com.siatsenko.movieland.entity.Movie;

import java.util.List;
import java.util.Map;

public interface MovieService {

    List<Movie> getAll(Map<String, String> queryMap);

    List<Movie> getRandom();

    List<Movie> getByGenreId(int genreId);
}
