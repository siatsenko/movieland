package com.siatsenko.movieland.service.impl;

import com.siatsenko.movieland.dao.GenreDao;
import com.siatsenko.movieland.entity.Genre;
import com.siatsenko.movieland.service.GenreService;
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

    @Autowired
    public void setGenreDao(GenreDao genreDao) {
        this.genreDao = genreDao;
    }
}