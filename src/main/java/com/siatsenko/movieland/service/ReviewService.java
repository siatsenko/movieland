package com.siatsenko.movieland.service;

import com.siatsenko.movieland.entity.common.Movie;
import com.siatsenko.movieland.entity.common.Review;
import com.siatsenko.movieland.entity.request.ReviewRequest;
import com.siatsenko.movieland.entity.common.User;

import java.util.List;

public interface ReviewService {

    Movie enrich(Movie movie);

    List<Review> getByMovieId(int movieId);

    void add(ReviewRequest reviewRequest, User user);

}
