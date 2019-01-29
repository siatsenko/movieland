package com.siatsenko.movieland.service;

import com.siatsenko.movieland.entity.common.Role;
import com.siatsenko.movieland.entity.common.Session;
import com.siatsenko.movieland.entity.common.User;

public interface AuthService {

    Session login(String email, String password);

    User getUser(String token);

    void logout(String token);

    boolean checkRoleLevel(User user, Role role);

}
