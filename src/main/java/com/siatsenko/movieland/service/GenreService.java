package com.siatsenko.movieland.service;

import com.siatsenko.movieland.entity.Genre;
import com.siatsenko.movieland.entity.Movie;

import java.util.List;

public interface GenreService {

    List<Genre> getAll();

    Movie enrich(Movie movie);

}
