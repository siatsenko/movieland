package com.siatsenko.movieland.dao;

import com.siatsenko.movieland.entity.common.Country;

import java.util.List;

public interface CountryDao {

    List<Country> getByMovieId(int movieId);

    List<Country> getAll();

    void editByMovieId(int movieId, int[] countryIds);
}
