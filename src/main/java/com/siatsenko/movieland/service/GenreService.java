package com.siatsenko.movieland.service;

import com.siatsenko.movieland.entity.common.Genre;
import com.siatsenko.movieland.entity.common.Movie;

import java.util.List;

public interface GenreService {

    List<Genre> getAll();

    List<Genre> getByMovieId(int movieId);

    Movie enrich(Movie movie);

    void editByMovieId(int movieId, int[] genreIds);

}
