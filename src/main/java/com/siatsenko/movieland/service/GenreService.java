package com.siatsenko.movieland.service;

import com.siatsenko.movieland.entity.Genre;
import com.siatsenko.movieland.entity.Movie;
import com.siatsenko.movieland.entity.MovieRequest;

import java.util.List;

public interface GenreService {

    List<Genre> getAll();

    List<Genre> getByMovieId(int movieId);

    Movie enrich(Movie movie);

    void editByMovieId(int movieId, int[] genreIds);

}
