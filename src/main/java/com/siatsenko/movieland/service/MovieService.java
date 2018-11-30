package com.siatsenko.movieland.service;

import com.siatsenko.movieland.entity.Movie;
import com.siatsenko.movieland.entity.RequestParams;

import java.util.List;

public interface MovieService {

    List<Movie> getAll(RequestParams requestParams);

    List<Movie> getRandom();

    List<Movie> getByGenreId(int genreId, RequestParams requestParams);
}
