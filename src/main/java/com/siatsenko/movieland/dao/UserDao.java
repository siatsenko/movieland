package com.siatsenko.movieland.dao;

import com.siatsenko.movieland.entity.User;

public interface UserDao {

    User getByReviewId(int reviewId);

}
