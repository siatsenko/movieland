package com.siatsenko.movieland.service.impl;

import com.siatsenko.movieland.dao.GenreDao;
import com.siatsenko.movieland.entity.Genre;
import com.siatsenko.movieland.service.CachedService;
import com.siatsenko.movieland.service.GenreService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.LinkedList;
import java.util.List;

@EnableScheduling
@Service("primaryGenreService")
public class CachedGenreService implements GenreService, CachedService {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    private List<Genre> cacheGenres;

    private volatile GenreDao genreDao;

    @Override
    public List<Genre> getAll() {
        logger.debug("getAll: start");
        List<Genre> result = new LinkedList<>();
            for (Genre cacheGenre : cacheGenres) {
                try {
                    result.add((Genre) cacheGenre.clone());
                } catch (CloneNotSupportedException e) {
                    e.printStackTrace();
                }
        }
        logger.trace("start finished and return result: {}", result);
        return result;
    }

    @Override
    @PostConstruct
    @Scheduled(fixedDelay = 4*60*1000, initialDelay = 4*60*1000)
    public Object refresh() {
        logger.debug("refresh: start");
        List<Genre> result = genreDao.getAll();
            cacheGenres = result;
        return result;
    }

    @Autowired
    public void setGenreDao(GenreDao genreDao) {
        this.genreDao = genreDao;
    }

}
