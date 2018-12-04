package com.siatsenko.movieland.dao;

import com.siatsenko.movieland.entity.Country;

import java.util.List;

public interface CountryDao {

    List<Country> getByMovieId(int movieId);

}
