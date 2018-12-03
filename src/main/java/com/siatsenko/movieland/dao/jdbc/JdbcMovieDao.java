package com.siatsenko.movieland.dao.jdbc;

import com.siatsenko.movieland.dao.MovieDao;
import com.siatsenko.movieland.dao.jdbc.mapper.MovieDetailRowMapper;
import com.siatsenko.movieland.dao.jdbc.mapper.MovieRowMapper;
import com.siatsenko.movieland.dao.jdbc.sql.SqlBuilder;
import com.siatsenko.movieland.entity.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JdbcMovieDao implements MovieDao {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    private JdbcTemplate jdbcTemplate;
    private SqlBuilder sqlBuilder;
    private int randomCount;
    private MovieRowMapper movieRowMapper;
    private MovieDetailRowMapper movieDetailRowMapper;
    private String allMoviesSql;
    private String randomMoviesSql;
    private String moviesByGenreIdSql;
    private String movieByIdSql;

    @Override
    public List<Movie> getAll(RequestParameters requestParameters) {
        String query = sqlBuilder.setOrder(allMoviesSql, requestParameters);
        logger.trace("getAll used query: {}", query);
        List<Movie> movies = jdbcTemplate.query(query, movieRowMapper);
        logger.trace("getAll finished and return movies: {}", movies);
        return movies;
    }

    @Override
    public List<Movie> getRandom() {
        List<Movie> movies = jdbcTemplate.query(randomMoviesSql, movieRowMapper, randomCount);
        logger.trace("getRandom finished and return movies: {}", movies);
        return movies;
    }

    @Override
    public List<Movie> getByGenreId(int genreId, RequestParameters requestParameters) {
        String query = sqlBuilder.setOrder(moviesByGenreIdSql, requestParameters);
        logger.trace("getByGenreId used query: {}", query);
        List<Movie> movies = jdbcTemplate.query(query, movieRowMapper, genreId);
        logger.trace("getByGenreId({}) finished and return movies: {}", genreId, movies);
        return movies;
    }

    @Override
    public Movie getById(int id) {
        Movie movie = null;
        List<Movie> movies = jdbcTemplate.query(movieByIdSql, movieDetailRowMapper, id);
        if (movies.size() > 0) {
            movie = movies.get(0);
        }

        logger.trace("getById({}) finished and return movies: {}", id, movie);
        return movie;
    }

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Autowired
    public void setAllMoviesSql(String allMoviesSql) {
        this.allMoviesSql = allMoviesSql;
    }

    @Autowired
    public void setRandomMoviesSql(String randomMoviesSql) {
        this.randomMoviesSql = randomMoviesSql;
    }

    @Autowired
    public void setMoviesByGenreIdSql(String moviesByGenreIdSql) {
        this.moviesByGenreIdSql = moviesByGenreIdSql;
    }

    @Autowired
    public void setMovieRowMapper(MovieRowMapper movieRowMapper) {
        this.movieRowMapper = movieRowMapper;
    }

    @Value("${random.count:5}")
    public void setRandomCount(int randomCount) {
        this.randomCount = randomCount;
    }

    @Autowired
    public void setSqlBuilder(SqlBuilder sqlBuilder) {
        this.sqlBuilder = sqlBuilder;
    }

    @Autowired
    public void setMovieByIdSql(String movieByIdSql) {
        this.movieByIdSql = movieByIdSql;
    }

    @Autowired
    public void setMovieDetailRowMapper(MovieDetailRowMapper movieDetailRowMapper) {
        this.movieDetailRowMapper = movieDetailRowMapper;
    }
}
