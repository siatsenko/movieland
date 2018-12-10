package com.siatsenko.movieland.service;

import com.siatsenko.movieland.entity.Session;

public interface AuthService {

    Session login(String email, String password);

    void logout(String token);

}
