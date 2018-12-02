package com.siatsenko.movieland.dao;

import com.siatsenko.movieland.entity.Movie;
import com.siatsenko.movieland.entity.RequestParams;

import java.util.List;

public interface MovieDao {

    List<Movie> getAll(RequestParams requestParams);

    List<Movie> getRandom();

    List<Movie> getByGenreId(int genreId, RequestParams requestParams);

    List<Movie> getById(int id);
}
