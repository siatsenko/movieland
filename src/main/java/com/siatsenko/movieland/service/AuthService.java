package com.siatsenko.movieland.service;

import com.siatsenko.movieland.entity.Role;
import com.siatsenko.movieland.entity.Session;
import com.siatsenko.movieland.entity.User;

public interface AuthService {

    Session login(String email, String password);

    User getUser(String token);

    void logout(String token);

    boolean checkRoleLevel(User user, Role role);

}
