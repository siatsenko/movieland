package com.siatsenko.movieland.service.impl;

import com.siatsenko.movieland.dao.CountryDao;
import com.siatsenko.movieland.entity.common.Country;
import com.siatsenko.movieland.entity.common.Movie;
import com.siatsenko.movieland.service.CountryService;
import com.siatsenko.movieland.service.SlowService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DefaultCountryService implements CountryService {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    private CountryDao countryDao;

    @Override
    public Movie enrich(Movie movie) {
        List<Country> countries = countryDao.getByMovieId(movie.getId());
        movie.setCountries(countries);
        logger.trace("enrich({}) finished and enrich countries: {}", movie.getId(), countries);
        return movie;
    }

    @Override
    public List<Country> getAll() {
        List<Country> countries = countryDao.getAll();
        logger.trace("getAll finished and return countries: {}", countries);
        return countries;
    }

    @Override
    public List<Country> getByMovieId(int movieId) {
        return countryDao.getByMovieId(movieId);
    }

    @Override
    public void editByMovieId(int movieId, int[] countryIds) {
        countryDao.editByMovieId(movieId, countryIds);
        logger.trace("editByMovieId({},{}) finished", movieId, countryDao);
    }

    @Autowired
    public void setCountryDao(CountryDao countryDao) {
        this.countryDao = countryDao;
    }

}
