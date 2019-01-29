package com.siatsenko.movieland.service;

import com.siatsenko.movieland.entity.common.Movie;
import com.siatsenko.movieland.entity.request.MovieRequest;
import com.siatsenko.movieland.entity.request.RequestParameters;
import com.siatsenko.movieland.entity.common.User;

import java.util.List;

public interface MovieService {

    List<Movie> getAll(RequestParameters requestParameters);

    List<Movie> getRandom();

    List<Movie> getByGenreId(int genreId, RequestParameters requestParameters);

    Movie getById(int id);

    Movie getById(int id, String currencyCode);

    Movie upsert(MovieRequest movieRequest, User user);

}
