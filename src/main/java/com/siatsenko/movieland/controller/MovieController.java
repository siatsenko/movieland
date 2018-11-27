package com.siatsenko.movieland.controller;

import com.siatsenko.movieland.entity.Genre;
import com.siatsenko.movieland.entity.Movie;
import com.siatsenko.movieland.service.GenreService;
import com.siatsenko.movieland.service.MovieService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MovieController {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    private MovieService movieService;
    private GenreService genreService;

    @RequestMapping(path = "/movie", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<Movie> getAllMovie() {
        logger.info("Sending request to get all movies");
        List<Movie> movies = movieService.getAll();
        logger.debug("Returning {} movies", movies.size());
        return movies;
    }

    @RequestMapping(path = "/movie/random", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<Movie> getRandomMovie() {
        logger.info("Sending request to get random movies");
        List<Movie> movies = movieService.getRandom();
        logger.debug("Returning {} movies", movies.size());
        return movies;
    }

    @RequestMapping(path = "/movie/genre/{genreId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<Movie> getMovieByGenreId(@PathVariable("genreId") int genreId) {
        logger.info("Sending request to get movies by genreId : {}", genreId);
        List<Movie> movies = movieService.getByGenreId(genreId);
        logger.debug("Returning {} movies", movies.size());
        return movies;
    }

    @RequestMapping(path = "/genre", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<Genre> getAllGenre() {
        logger.info("Sending request to get all genres");
        List<Genre> genres = genreService.getAll();
        logger.debug("Returning {} genres", genres.size());
        return genres;
    }

    @Autowired
    public void setMovieService(MovieService movieService) {
        this.movieService = movieService;
    }

    @Autowired
    @Qualifier("primaryGenreService")
    public void setGenreService(GenreService genreService) {
        this.genreService = genreService;
    }
}
