package com.siatsenko.movieland.dao.jdbc;

import com.siatsenko.movieland.dao.MovieDao;
import com.siatsenko.movieland.dao.jdbc.mapper.MovieRowMapper;
import com.siatsenko.movieland.dao.jdbc.sql.SqlBuilder;
import com.siatsenko.movieland.entity.Movie;
import com.siatsenko.movieland.entity.RequestParams;
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
    private String allMovieSql;
    private String randomMovieSql;
    private String movieByGenreIdSql;
    private MovieRowMapper movieRowMapper;
    private int randomCount;
    private SqlBuilder sqlBuilder;

    @Override
    public List<Movie> getAll(RequestParams requestParams) {
        String query = sqlBuilder.setOrder(allMovieSql, requestParams);
        logger.trace("getAll used query: {}", query);
        List<Movie> movies = jdbcTemplate.query(query, movieRowMapper);
        logger.trace("getAll finished and return movies: {}", movies);
        return movies;
    }

    @Override
    public List<Movie> getRandom() {
        List<Movie> movies = jdbcTemplate.query(randomMovieSql, movieRowMapper, randomCount);
        logger.trace("getRandom finished and return movies: {}", movies);
        return movies;
    }

    @Override
    public List<Movie> getByGenreId(int genreId, RequestParams requestParams) {
        String query = sqlBuilder.setOrder(movieByGenreIdSql, requestParams);
        logger.trace("getByGenreId used query: {}", query);
        List<Movie> movies = jdbcTemplate.query(query, movieRowMapper, genreId);
        logger.trace("getByGenreId genreId: {} finished and return movies: {}", genreId, movies);
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
    public void setMovieByGenreIdSql(String movieByGenreIdSql) {
        this.movieByGenreIdSql = movieByGenreIdSql;
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

}
