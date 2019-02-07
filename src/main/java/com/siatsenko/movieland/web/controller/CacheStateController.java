package com.siatsenko.movieland.web.controller;

import com.siatsenko.movieland.service.impl.CachedMovieService;
import com.siatsenko.movieland.web.dto.CacheMovieDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Endpoint(id = "cache-state")
public class CacheStateController {

    private CachedMovieService cachedMovieService;

    @ReadOperation
    public List<CacheMovieDto> cacheStateWeb() {
        return cachedMovieService.cacheStateWeb();
    }

    @Autowired
    public void setCachedMovieService(CachedMovieService cachedMovieService) {
        this.cachedMovieService = cachedMovieService;
    }
}
