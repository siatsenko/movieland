package com.siatsenko.movieland.controller;

import com.siatsenko.movieland.entity.Movie;
import com.siatsenko.movieland.entity.MovieRequest;
import com.siatsenko.movieland.entity.RequestParameters;
import com.siatsenko.movieland.service.*;
import com.siatsenko.movieland.web.controller.util.DtoConverter;
import com.siatsenko.movieland.web.dto.MovieDetailDto;
import com.siatsenko.movieland.web.dto.MovieDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class MovieController {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    private MovieService movieService;
    private DtoConverter dtoConverter;

    private RequestParamsService requestParamsService;

    @GetMapping(path = "/movie", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<MovieDto> getAll(@RequestParam Map<String, String> queryMap) {
        logger.info("Sending request to get all movies");
        logger.debug("Sending request to get all movies {}", queryMap);
        RequestParameters requestParameters = requestParamsService.setSortings(queryMap);
        List<Movie> movies = movieService.getAll(requestParameters);
        logger.debug("getAll returning {} movies", movies.size());
        return dtoConverter.asMovieDto(movies);
    }

    @GetMapping(path = "/movie/random", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<MovieDto> getRandom() {
        logger.info("Sending request to get random movies");
        List<Movie> movies = movieService.getRandom();
        logger.debug("getRandom returning {} movies", movies.size());
        return dtoConverter.asMovieDto(movies);
    }

    @GetMapping(path = "/movie/genre/{genreId}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<MovieDto> getByGenreId(@PathVariable("genreId") int genreId, @RequestParam Map<String, String> queryMap) {
        logger.info("Sending request to get movies by genreId : {}", genreId);
        RequestParameters requestParameters = requestParamsService.setSortings(queryMap);
        List<Movie> movies = movieService.getByGenreId(genreId, requestParameters);
        logger.debug("getByGenreId returning {} movies", movies.size());
        return dtoConverter.asMovieDto(movies);
    }

    @GetMapping(path = "/movie/{movieId}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public MovieDetailDto getById(@PathVariable("movieId") int id, @RequestParam(name = "currency", defaultValue = "UAH") String currencyCode) {
        logger.info("Sending request to get movie by id : {}, currency code : {}", id, currencyCode);
        Movie movie;
        if ("UAH".equals(currencyCode)) {
            movie = movieService.getById(id);
        } else {
            movie = movieService.getById(id, currencyCode);
        }
        logger.debug("getById returning {} movie", movie);
        return dtoConverter.asMovieDetailDto(movie);
    }

    @PostMapping(path = "/movie", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public void add(@RequestHeader(name = "uuid", defaultValue = "0000") String token, @RequestBody MovieRequest movieRequest) {
        movieService.add(movieRequest, token);
        logger.debug("add {}:", token);
    }

    @PutMapping(path = "/movie/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public void edit(@PathVariable("id") int id, @RequestHeader(name = "uuid", defaultValue = "0000") String token, @RequestBody MovieRequest movieRequest) {
        movieService.edit(id, movieRequest, token);
        logger.debug("edit {}:", token);
    }

    @Autowired
    public void setMovieService(MovieService movieService) {
        this.movieService = movieService;
    }

    @Autowired
    public void setRequestParamsService(RequestParamsService requestParamsService) {
        this.requestParamsService = requestParamsService;
    }

    @Autowired
    public void setDtoConverter(DtoConverter dtoConverter) {
        this.dtoConverter = dtoConverter;
    }
}
