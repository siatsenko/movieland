package com.siatsenko.movieland.dao;

import com.siatsenko.movieland.entity.common.Movie;
import com.siatsenko.movieland.entity.request.RequestParameters;

import java.util.List;

public interface MovieDao {

    List<Movie> getAll(RequestParameters requestParameters);

    List<Movie> getRandom();

    List<Movie> getByGenreId(int genreId, RequestParameters requestParameters);

    Movie getById(int id);

    Movie upsert(Movie movie);

}
