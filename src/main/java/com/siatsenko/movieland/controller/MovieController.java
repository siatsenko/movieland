package com.siatsenko.movieland.controller;

import com.siatsenko.movieland.entity.Movie;
import com.siatsenko.movieland.service.MovieService;
import com.sun.javafx.collections.MappingChange;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class MovieController {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    private MovieService movieService;

    @RequestMapping(path = "/movie", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<Movie> getAll(@RequestParam Map<String, String> queryMap) {
        logger.info("Sending request to get all movies");
        logger.debug("Sending request to get all movies {}", queryMap.toString());
        List<Movie> movies = movieService.getAll(queryMap);
        logger.debug("Returning {} movies", movies.size());
        return movies;
    }

    @RequestMapping(path = "/movie/random", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<Movie> getRandom() {
        logger.info("Sending request to get random movies");
        List<Movie> movies = movieService.getRandom();
        logger.debug("Returning {} movies", movies.size());
        return movies;
    }

    @RequestMapping(path = "/movie/genre/{genreId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<Movie> getByGenreId(@PathVariable("genreId") int genreId) {
        logger.info("Sending request to get movies by genreId : {}", genreId);
        List<Movie> movies = movieService.getByGenreId(genreId);
        logger.debug("Returning {} movies", movies.size());
        return movies;
    }

    @Autowired
    public void setMovieService(MovieService movieService) {
        this.movieService = movieService;
    }

}
