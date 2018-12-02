package com.siatsenko.movieland.dao;

import com.siatsenko.movieland.entity.User;

import java.util.List;

public interface UserDao {

    User getByReviewId(int reviewId);

}
