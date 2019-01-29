package com.siatsenko.movieland.dao.jdbc;

import com.siatsenko.movieland.dao.MovieDao;
import com.siatsenko.movieland.dao.jdbc.mapper.MovieDetailRowMapper;
import com.siatsenko.movieland.dao.jdbc.mapper.MovieRowMapper;
import com.siatsenko.movieland.dao.jdbc.sql.SqlBuilder;
import com.siatsenko.movieland.entity.common.Movie;
import com.siatsenko.movieland.entity.request.RequestParameters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JdbcMovieDao implements MovieDao {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private SqlBuilder sqlBuilder;

    @Value("${randoms.count:5}")
    private int randomCount;

    private MovieRowMapper movieRowMapper;
    private MovieDetailRowMapper movieDetailRowMapper;
    private String allMoviesSql;
    private String randomMoviesSql;
    private String moviesByGenreIdSql;
    private String movieByIdSql;
    private String addMovieSql;
    private String editMovieSql;

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
        Movie movie = jdbcTemplate.queryForObject(movieByIdSql, movieDetailRowMapper, id);
        logger.trace("getById({}) finished and return movies: {}", id, movie);
        return movie;
    }

    @Override
    public Movie upsert(Movie movie) {
        if (movie.getId() == 0) {
            return update(movie, addMovieSql, false);
        } else {
            return update(movie, editMovieSql, true);
        }
    }

    private Movie update(Movie movie, String querySql, boolean withId) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource()
                .addValue("nameRussian", movie.getNameRussian())
                .addValue("nameNative", movie.getNameNative())
                .addValue("yearOfRelease", movie.getYearOfRelease())
                .addValue("price", movie.getPrice())
                .addValue("rating", movie.getRating())
                .addValue("description", movie.getDescription())
                .addValue("picturePath", movie.getPicturePath());

        if (withId) {
            mapSqlParameterSource.addValue("id", movie.getId());
        }

        String[] returningArray = new String[]{"id"};

        namedParameterJdbcTemplate.update(querySql, mapSqlParameterSource, keyHolder, returningArray);
        int movieId = keyHolder.getKey().intValue();
        movie.setId(movieId);

        logger.trace("add() finished and return movie: {}", movie);
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

    @Autowired
    public void setAddMovieSql(String addMovieSql) {
        this.addMovieSql = addMovieSql;
    }

    @Autowired
    public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Autowired
    public void setEditMovieSql(String editMovieSql) {
        this.editMovieSql = editMovieSql;
    }
}
