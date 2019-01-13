package com.siatsenko.movieland.service;

import com.siatsenko.movieland.entity.Country;
import com.siatsenko.movieland.entity.Movie;

import java.util.List;

public interface CountryService {

    Movie enrich(Movie movie);

    List<Country> getAll();

    List<Country> getByMovieId(int movieId);

    void editByMovieId(int movieId, int[] countryIds);

}
