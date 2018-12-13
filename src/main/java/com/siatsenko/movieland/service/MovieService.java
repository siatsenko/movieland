package com.siatsenko.movieland.service;

import com.siatsenko.movieland.entity.Movie;
import com.siatsenko.movieland.entity.MovieRequest;
import com.siatsenko.movieland.entity.RequestParameters;
import com.siatsenko.movieland.entity.User;

import java.util.List;

public interface MovieService {

    List<Movie> getAll(RequestParameters requestParameters);

    List<Movie> getRandom();

    List<Movie> getByGenreId(int genreId, RequestParameters requestParameters);

    Movie getById(int id);

    Movie getById(int id, String currencyCode);

    Movie upsert(MovieRequest movieRequest, User user);

}
