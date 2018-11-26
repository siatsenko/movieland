package com.siatsenko.movieland.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.siatsenko.movieland.entity.Movie;
import com.siatsenko.movieland.service.MovieService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MovieController {
    private final Logger log = LoggerFactory.getLogger(getClass());
    private MovieService movieService;

    @RequestMapping(path = "/movie", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<Movie> getAll() throws JsonProcessingException {
        log.info("Sending request to get all movies");
        List<Movie> movies = movieService.getAll();
        log.info("Movies is received");
        log.debug("Movies is received. moviesJson: {}", new ObjectMapper().writeValueAsString(movies));
        return movies;
    }

    @RequestMapping(path = "/movie/random", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<Movie> getRandom() throws JsonProcessingException {
        log.info("Sending request to get random movies");
        List<Movie> movies = movieService.getRandom();
        log.info("Movies is received");
        log.debug("Movies is received. moviesJson: {}", new ObjectMapper().writeValueAsString(movies));
        return movies;
    }

    @Autowired
    public void setMovieService(MovieService movieService) {
        this.movieService = movieService;
    }

}
