package com.siatsenko.movieland.dao;

import com.siatsenko.movieland.entity.common.Genre;

import java.util.List;

public interface GenreDao {

    List<Genre> getAll();

    List<Genre> getByMovieId(int movieId);

    void editByMovieId(int movieId, int[] genreIds);
}
