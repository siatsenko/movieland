package com.siatsenko.movieland.service;

import com.siatsenko.movieland.entity.Movie;
import com.siatsenko.movieland.entity.ReviewRequest;
import com.siatsenko.movieland.entity.User;

public interface ReviewService {

    Movie enrich(Movie movie);

    void add(ReviewRequest reviewRequest, User user);

}
