package com.siatsenko.movieland.service;

import com.siatsenko.movieland.entity.Movie;
import com.siatsenko.movieland.entity.RequestParameters;

import java.util.List;

public interface MovieService {

    List<Movie> getAll(RequestParameters requestParameters);

    List<Movie> getRandom();

    List<Movie> getByGenreId(int genreId, RequestParameters requestParameters);

    Movie getById(int id, String currencyCode);

}
