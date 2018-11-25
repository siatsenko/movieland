package com.siatsenko.movieland.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.siatsenko.movieland.entity.Movie;
import com.siatsenko.movieland.service.MovieService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MovieController {
    private final Logger log = LoggerFactory.getLogger(getClass());
    private MovieService movieService;
    private int randomCount;

    @RequestMapping(path = "/movie", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String getAll() throws JsonProcessingException {
        log.info("Sending request to get all movies");

        ObjectMapper objectMapper = new ObjectMapper();
        List<Movie> movies = movieService.getAll();
        String moviesJson = objectMapper.writeValueAsString(movies);

        log.info("Movie is received");
        log.trace("Movie is received. moviesJson: {}", moviesJson);
        return moviesJson;
    }

    @Autowired
    public void setMovieService(MovieService movieService) {
        this.movieService = movieService;
    }

    @Value("${random.count:3}")
    public void setRandomCount(int randomCount) {
        this.randomCount = randomCount;
    }

}
