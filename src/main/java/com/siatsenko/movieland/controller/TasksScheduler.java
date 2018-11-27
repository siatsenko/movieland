package com.siatsenko.movieland.controller;

import com.siatsenko.movieland.service.CachedService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class TasksScheduler {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    private CachedService cachedService;

    @Scheduled(fixedDelay = 60*1000, initialDelay = 60*1000)
    public void cachedService() {
        logger.info("The time is now {}", dateFormat.format(new Date()));
        cachedService.refresh();
    }

    @Autowired
    public void setCachedService(CachedService cachedService) {
        this.cachedService = cachedService;
    }
}
