package com.siatsenko.movieland.dao;

import com.siatsenko.movieland.entity.common.User;

import java.util.List;

public interface UserDao {

    List<User> getByIds(List<Integer> userIds);

    User getByAuth(String email, String password);

}
