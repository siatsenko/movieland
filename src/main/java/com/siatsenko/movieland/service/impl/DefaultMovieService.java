package com.siatsenko.movieland.service.impl;

import com.siatsenko.movieland.dao.MovieDao;
import com.siatsenko.movieland.entity.Movie;
import com.siatsenko.movieland.entity.RequestParameters;
import com.siatsenko.movieland.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DefaultMovieService implements MovieService {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    private MovieDao movieDao;
    private CurrencyService currencyService;
    private EnrichmentService enrichmentService;

    @Override
    public List<Movie> getAll(RequestParameters requestParameters) {
        List<Movie> movies = movieDao.getAll(requestParameters);
        logger.trace("getAll finished and return movies: {}", movies);
        return movies;
    }

    @Override
    public List<Movie> getRandom() {
        List<Movie> movies = movieDao.getRandom();
        logger.trace("getRandom finished and return movies: {}", movies);
        return movies;
    }

    @Override
    public List<Movie> getByGenreId(int genreId, RequestParameters requestParameters) {
        List<Movie> movies = movieDao.getByGenreId(genreId, requestParameters);
        logger.trace("getByGenreId({}) finished and return movies: {}", genreId, movies);
        return movies;
    }

    @Override
    public Movie getById(int id) {
        Movie movie = movieDao.getById(id);
        enrichmentService.enrich(movie);

        logger.trace("getById({}) finished and return movies: {}", id, movie);
        return movie;
    }

    @Override
    public Movie getById(int id, String currencyCode) {
        Movie movie = getById(id);
        currencyService.enrich(movie, currencyCode);

        logger.trace("getById({}) finished and return movies: {}", id, movie);
        return movie;
    }

    @Autowired
    public void setMovieDao(MovieDao movieDao) {
        this.movieDao = movieDao;
    }

    @Autowired
    public void setCurrencyService(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    @Autowired
    public void setEnrichmentService(EnrichmentService enrichmentService) {
        this.enrichmentService = enrichmentService;
    }
}
