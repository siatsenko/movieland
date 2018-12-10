package com.siatsenko.movieland.service;

import com.siatsenko.movieland.entity.Session;
import com.siatsenko.movieland.entity.User;

public interface AuthService {

    Session login(String email, String password);

    void logout(String token);

}
