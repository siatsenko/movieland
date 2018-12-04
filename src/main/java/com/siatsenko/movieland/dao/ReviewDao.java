package com.siatsenko.movieland.dao;

import com.siatsenko.movieland.entity.Review;

import java.util.List;

public interface ReviewDao {

    List<Review> getByMovieId(int movieId);

}
