package com.siatsenko.movieland.dao;

import com.siatsenko.movieland.entity.User;

import java.util.List;
import java.util.Map;

public interface UserDao {

    List<User> getByIds(List<Integer> userIds);

}
