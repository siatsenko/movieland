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

@Repository("MovieDao")
public class JdbcMovieDao implements MovieDao {
    private final Logger log = LoggerFactory.getLogger(getClass());

    private JdbcTemplate jdbcTemplate;
    private String getAllSql;
    private MovieRowMapper movieRowMapper;

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Autowired
    public void setGetAllSql(String getAllSql) {
        this.getAllSql = getAllSql;
    }

    @Autowired
    public void setMovieRowMapper(MovieRowMapper movieRowMapper) {
        this.movieRowMapper = movieRowMapper;
    }

    @Override
    public List<Movie> getAll() {
        log.info("GetAll started");

        List<Movie> movies = jdbcTemplate.query(getAllSql, movieRowMapper);

        log.info("GetAll finished");
        log.debug("GetAll finished and return movies: {}", movies);
        return movies;
    }

}
