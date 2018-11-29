package com.siatsenko.movieland.service.impl;

import com.siatsenko.movieland.dao.MovieDao;
import com.siatsenko.movieland.entity.Movie;
import com.siatsenko.movieland.service.MovieService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DefaultMovieService implements MovieService {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    private MovieDao movieDao;

    @Override
    public List<Movie> getAll(String order) {
        List<Movie> movies = movieDao.getAll(order);
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
    public List<Movie> getByGenreId(int genreId, String order) {
        List<Movie> movies = movieDao.getByGenreId(genreId, order);
        logger.trace("getByGenreId genreId: {} finished and return movies: {}", genreId, movies);
        return movies;
    }

    @Autowired
    public void setMovieDao(MovieDao movieDao) {
        this.movieDao = movieDao;
    }

}
