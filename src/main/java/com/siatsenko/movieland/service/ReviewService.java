package com.siatsenko.movieland.service;

import com.siatsenko.movieland.entity.Movie;
import com.siatsenko.movieland.entity.ReviewRequest;

public interface ReviewService {

    Movie enrich(Movie movie);

    void add(ReviewRequest reviewRequest, String token);

}
