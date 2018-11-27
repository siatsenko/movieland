package com.siatsenko.movieland.service;

import java.util.List;

public interface CachedService {

    Object start();

    Object refresh();

    boolean isValid();

}
