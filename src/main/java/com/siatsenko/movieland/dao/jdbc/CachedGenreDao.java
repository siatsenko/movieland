package com.siatsenko.movieland.dao.jdbc;

import com.siatsenko.movieland.dao.GenreDao;
import com.siatsenko.movieland.entity.common.Genre;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Primary;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Repository
@Primary
@CacheConfig(cacheNames={"cacheGenres"})
public class CachedGenreDao implements GenreDao {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Qualifier("jdbcGenreDao")
    private GenreDao jdbcGenreDao;

    private volatile List<Genre> cacheGenres = new ArrayList<>();

    @Cacheable
    @Override
    public List<Genre> getAll() {
        logger.debug("getAll: start");
        List<Genre> result = jdbcGenreDao.getAll();
        logger.trace("getAll: finished and return result: {}", result);
        return result;
    }

    @Override
    public List<Genre> getByMovieId(int movieId) {
        List<Genre> genres = jdbcGenreDao.getByMovieId(movieId);
        logger.trace("getByMovieId({}) finished and return genres: {}", movieId, genres);
        return genres;
    }

    @Override
    public void editByMovieId(int movieId, int[] genreIds) {
        jdbcGenreDao.editByMovieId(movieId, genreIds);
        logger.trace("editByMovieId({},{}) finished", movieId, genreIds);
    }

    @Autowired
    public void setJdbcGenreDao(GenreDao jdbcGenreDao) {
        this.jdbcGenreDao = jdbcGenreDao;
    }

}
