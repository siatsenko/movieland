package com.siatsenko.movieland.service;

import com.siatsenko.movieland.entity.Review;

import java.util.List;

public interface UserService {

    List<Review> enrich(List<Review> reviews);

}
