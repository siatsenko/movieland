package com.siatsenko.movieland.dao.jdbc;

import ch.qos.logback.core.joran.spi.DefaultClass;
import com.siatsenko.movieland.dao.CachedDao;
import com.siatsenko.movieland.dao.GenreDao;
import com.siatsenko.movieland.dao.jdbc.mapper.GenreRowMapper;
import com.siatsenko.movieland.entity.Genre;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.LinkedList;
import java.util.List;

@Repository
@Primary
public class JdbcCachedGenreDao implements GenreDao, CachedDao {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    private JdbcTemplate jdbcTemplate;
    private String allGenreSql;
    private GenreRowMapper genreRowMapper;
    private volatile List<Genre> cacheGenres;

    @Override
    public List<Genre> getAll() {
        logger.debug("getAll: start");
        List<Genre> result = new LinkedList<>(cacheGenres);
        logger.trace("getAll: finished and return result: {}", result);
        return result;
    }

    @Override
    @PostConstruct
    @Scheduled(fixedDelayString = "${scheduled.genre.fixedDelay:30000}", initialDelayString = "${scheduled.genre.initialDelay:30000}")
    public Object refresh() {
        logger.debug("refresh: start");
        List<Genre> result = jdbcTemplate.query(allGenreSql, genreRowMapper);
        cacheGenres = result;
        logger.trace("refresh: finished and return result: {}", result);
        return result;
    }

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Autowired
    public void setAllGenreSql(String allGenreSql) {
        this.allGenreSql = allGenreSql;
    }

    @Autowired
    public void setGenreRowMapper(GenreRowMapper genreRowMapper) {
        this.genreRowMapper = genreRowMapper;
    }
}
