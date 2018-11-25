package com.siatsenko.movieland.dao.jdbc;

import com.siatsenko.movieland.dao.MovieDao;
import com.siatsenko.movieland.dao.jdbc.mapper.MovieRowMapper;
import com.siatsenko.movieland.entity.Movie;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JdbcMovieDao implements MovieDao {
    private final Logger log = LoggerFactory.getLogger(getClass());

    private JdbcTemplate jdbcTemplate;
    private String allMovieSql;
    private String randomMovieSql;
    private MovieRowMapper movieRowMapper;

    @Override
    public List<Movie> getAll() {
        List<Movie> movies = jdbcTemplate.query(allMovieSql, movieRowMapper);
        log.trace("GetAll finished and return movies: {}", movies);
        return movies;
    }

    @Override
    public List<Movie> getRandom() {
        List<Movie> movies = jdbcTemplate.query(randomMovieSql, movieRowMapper);
        log.trace("GetRandom finished and return movies: {}", movies);
        return movies;
    }

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Autowired
    public void setAllMovieSql(String allMovieSql) {
        this.allMovieSql = allMovieSql;
    }

    @Autowired
    public void setRandomMovieSql(String randomMovieSql) {
        this.randomMovieSql = randomMovieSql;
    }

    @Autowired
    public void setMovieRowMapper(MovieRowMapper movieRowMapper) {
        this.movieRowMapper = movieRowMapper;
    }

}
