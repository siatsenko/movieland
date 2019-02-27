package com.siatsenko.movieland.service.impl;

import com.siatsenko.movieland.service.SlowService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class DefaultSlowService implements SlowService {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Value("${debug.slowService.pause:5000}")
    private int pauseMillis;

    @Override
    public void slow(long millis) {
        logger.debug("SlowService.slow({}): start for thread {}", millis, Thread.currentThread().getName());
        long start = new Date().getTime();
        while (new Date().getTime() - start < millis) {
        }
        logger.debug("SlowService.slow({}): stop", millis);
    }

    @Override
    public void slow() {
        slow(pauseMillis);
    }
}
