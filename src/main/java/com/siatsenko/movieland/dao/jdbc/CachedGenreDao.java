package com.siatsenko.movieland.dao.jdbc;

import com.siatsenko.movieland.dao.GenreDao;
import com.siatsenko.movieland.dao.jdbc.mapper.GenreRowMapper;
import com.siatsenko.movieland.entity.Genre;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Repository
@Primary
public class CachedGenreDao implements GenreDao {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Qualifier("jdbcGenreDao")
    private GenreDao jdbcGenreDao;

    private volatile List<Genre> cacheGenres;

    @Override
    public List<Genre> getAll() {
        logger.debug("getAll: start");
        List<Genre> result = new ArrayList<>(cacheGenres);
        logger.trace("getAll: finished and return result: {}", result);
        return result;
    }

    @PostConstruct
    @Scheduled(fixedDelayString = "${scheduled.genre.fixedDelay:30000}", initialDelayString = "${scheduled.genre.initialDelay:30000}")
    public Object refresh() {
        logger.debug("refresh: start");
        List<Genre> result = jdbcGenreDao.getAll();
        cacheGenres = result;
        logger.trace("refresh: finished and return result: {}", result);
        return result;
    }

    @Autowired
    public void setJdbcGenreDao(GenreDao jdbcGenreDao) {
        this.jdbcGenreDao = jdbcGenreDao;
    }
}