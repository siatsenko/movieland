package com.siatsenko.movieland.controller;

import com.siatsenko.movieland.entity.Movie;
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
    private DtoConverter dtoService;

    private RequestParamsService requestParamsService;

    @RequestMapping(path = "/movie", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<MovieDto> getAll(@RequestParam Map<String, String> queryMap) {
        logger.info("Sending request to get all movies");
        logger.debug("Sending request to get all movies {}", queryMap.toString());
        RequestParameters requestParameters = requestParamsService.setSortings(queryMap);
        List<Movie> movies = movieService.getAll(requestParameters);
        logger.debug("Returning {} movies", movies.size());
        return dtoService.asMovieDto(movies);
    }

    @RequestMapping(path = "/movie/random", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<MovieDto> getRandom() {
        logger.info("Sending request to get random movies");
        List<Movie> movies = movieService.getRandom();
        logger.debug("Returning {} movies", movies.size());
        return dtoService.asMovieDto(movies);
    }

    @RequestMapping(path = "/movie/genre/{genreId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<MovieDto> getByGenreId(@PathVariable("genreId") int genreId, @RequestParam Map<String, String> queryMap) {
        logger.info("Sending request to get movies by genreId : {}", genreId);
        RequestParameters requestParameters = requestParamsService.setSortings(queryMap);
        List<Movie> movies = movieService.getByGenreId(genreId, requestParameters);
        logger.debug("Returning {} movies", movies.size());
        return dtoService.asMovieDto(movies);
    }

    @RequestMapping(path = "/movie/{movieId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public MovieDetailDto getById(@PathVariable("movieId") int id) {
        logger.info("Sending request to get movie by id : {}", id);
        Movie movie = movieService.getById(id);
        logger.debug("Returning {} movie", movie);
        return dtoService.asMovieDetailDto(movie);
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
    public void setDtoService(DtoConverter dtoService) {
        this.dtoService = dtoService;
    }
}
