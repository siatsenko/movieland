package com.siatsenko.movieland.dao;

import com.siatsenko.movieland.entity.common.Review;

import java.util.List;

public interface ReviewDao {

    List<Review> getByMovieId(int movieId);

    void add(int movieId, Review review);

}
