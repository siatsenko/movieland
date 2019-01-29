package com.siatsenko.movieland.service;

import com.siatsenko.movieland.entity.common.Review;
import com.siatsenko.movieland.entity.common.User;

import java.util.List;

public interface UserService {

    List<Review> enrich(List<Review> reviews);

    User getByAuth(String email, String password);

}
