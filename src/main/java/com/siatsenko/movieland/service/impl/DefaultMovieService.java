package com.siatsenko.movieland.service.impl;

import com.siatsenko.movieland.dao.MovieDao;
import com.siatsenko.movieland.entity.*;
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
    private GenreService genreService;
    private CountryService countryService;
    private AuthService authService;

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

    @Override
    public Movie add(MovieRequest movieRequest, String token) {
        Movie movie = update(null, movieRequest, token);
        logger.trace("add({},{}) finished and return movie: {}", movieRequest, token, movie);
        return movie;
    }

    @Override
    public Movie edit(int id, MovieRequest movieRequest, String token) {
        Movie movie = update(id, movieRequest, token);
        logger.trace("edit({},{},{}) finished and return movie: {}", id, movieRequest, token, movie);
        return movie;
    }

    private Movie update(Integer id, MovieRequest movieRequest, String token) {
        authService.checkRoleLevel(token, Role.ADMIN);

        Movie movie = new Movie(movieRequest);
        if (id != null) {
            movie.setId(id);
            movie = movieDao.edit(movie);
        } else {
            movie = movieDao.add(movie);
        }

        genreService.editByMovieId(movie.getId(), movieRequest.getGenres());
        countryService.editByMovieId(movie.getId(), movieRequest.getCountries());

        logger.trace("update({},{},{}) finished and return movie: {}", id, movieRequest, token, movie);
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

    @Autowired
    public void setAuthService(AuthService authService) {
        this.authService = authService;
    }

    @Autowired
    public void setGenreService(GenreService genreService) {
        this.genreService = genreService;
    }

    @Autowired
    public void setCountryService(CountryService countryService) {
        this.countryService = countryService;
    }
}
