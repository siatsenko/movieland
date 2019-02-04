package com.siatsenko.movieland.service.impl;

import com.siatsenko.movieland.dao.GenreDao;
import com.siatsenko.movieland.entity.common.Genre;
import com.siatsenko.movieland.entity.common.Movie;
import com.siatsenko.movieland.service.GenreService;
import com.siatsenko.movieland.service.SlowService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DefaultGenreService implements GenreService {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    private GenreDao genreDao;

    @Override
    public List<Genre> getAll() {
        List<Genre> genres = genreDao.getAll();
        logger.trace("getAll finished and return genres: {}", genres);
        return genres;
    }

    @Override
    public List<Genre> getByMovieId(int movieId) {
        return genreDao.getByMovieId(movieId);
    }

    @Override
    public Movie enrich(Movie movie) {
        List<Genre> genres = genreDao.getByMovieId(movie.getId());
        movie.setGenres(genres);
        logger.trace("enrich({}) finished and enrich genres: {}", movie.getId(), genres);
        return movie;
    }

    @Override
    public void editByMovieId(int movieId, int[] genreIds) {
        genreDao.editByMovieId(movieId, genreIds);
        logger.trace("editByMovieId({},{}) finished", movieId, genreIds);
    }

    @Autowired
    public void setGenreDao(GenreDao genreDao) {
        this.genreDao = genreDao;
    }

}
