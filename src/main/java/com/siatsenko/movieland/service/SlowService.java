package com.siatsenko.movieland.service;

public interface SlowService {

    void slow(long millis) throws InterruptedException;

    void slow() throws InterruptedException;

}
