package com.siatsenko.movieland.service.impl;

import com.siatsenko.movieland.dao.GenreDao;
import com.siatsenko.movieland.entity.Genre;
import com.siatsenko.movieland.service.CachedService;
import com.siatsenko.movieland.service.GenreService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.LinkedList;
import java.util.List;

@Service("primaryGenreService")
public class CachedGenreService implements GenreService, CachedService {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    private List<Genre> cacheGenres;

    private GenreDao genreDao;

    @Override
    public List<Genre> getAll() {
        logger.debug("getAll: start");
        if (!isValid()) {
            logger.debug("getAll: not Valid");
            start();
        }
        List<Genre> result = new LinkedList<>();
        synchronized (cacheGenres) {
            logger.debug("getAll: synchronized block");
            for (Genre cacheGenre : cacheGenres) {
                try {
                    result.add((Genre) cacheGenre.clone());
                } catch (CloneNotSupportedException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }

    @Override
    @PostConstruct
    public Object start() {
        cacheGenres = genreDao.getAll();
        logger.trace("start finished and return cacheGenres: {}", cacheGenres);
        return cacheGenres;
    }

    @Override
    public Object refresh() {
        List<Genre> result = genreDao.getAll();
        synchronized (cacheGenres) {
            cacheGenres = result;
        }
        return result;
    }

    @Override
    public boolean isValid() {
        return cacheGenres != null;
    }

    @Autowired
    public void setGenreDao(GenreDao genreDao) {
        this.genreDao = genreDao;
    }

}
