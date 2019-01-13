package com.siatsenko.movieland.service;

import com.siatsenko.movieland.entity.Movie;
import com.siatsenko.movieland.entity.Review;
import com.siatsenko.movieland.entity.ReviewRequest;
import com.siatsenko.movieland.entity.User;

import java.util.List;

public interface ReviewService {

    Movie enrich(Movie movie);

    List<Review> getByMovieId(int movieId);

    void add(ReviewRequest reviewRequest, User user);

}
