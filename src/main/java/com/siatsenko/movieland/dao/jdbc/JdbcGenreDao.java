package com.siatsenko.movieland.dao.jdbc;

import com.siatsenko.movieland.dao.GenreDao;
import com.siatsenko.movieland.dao.jdbc.mapper.GenreRowMapper;
import com.siatsenko.movieland.entity.Genre;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JdbcGenreDao implements GenreDao {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    private JdbcTemplate jdbcTemplate;
    private GenreRowMapper genreRowMapper;
    private String allGenresSql;
    private String genresByMovieIdSql;
    private String editGenresByMovieIdSql;

    @Override
    public List<Genre> getAll() {
        List<Genre> genres = jdbcTemplate.query(allGenresSql, genreRowMapper);
        logger.trace("getAll finished and return genres: {}", genres);
        return genres;
    }

    @Override
    public List<Genre> getByMovieId(int movieId) {
        List<Genre> genres = jdbcTemplate.query(genresByMovieIdSql, genreRowMapper, movieId);
        logger.trace("getByMovieId({}) finished and return genres: {}", movieId, genres);
        return genres;
    }

    @Override
    public void editByMovieId(int movieId, int[] genreIds) {
        jdbcTemplate.update(editGenresByMovieIdSql, movieId, movieId, genreIds);
        logger.trace("editByMovieId({},{}) finished", movieId, genreIds);
    }

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Autowired
    public void setGenreRowMapper(GenreRowMapper genreRowMapper) {
        this.genreRowMapper = genreRowMapper;
    }

    @Autowired
    public void setAllGenresSql(String allGenresSql) {
        this.allGenresSql = allGenresSql;
    }

    @Autowired
    public void setGenresByMovieIdSql(String genresByMovieIdSql) {
        this.genresByMovieIdSql = genresByMovieIdSql;
    }

    @Autowired
    public void setEditGenresByMovieIdSql(String editGenresByMovieIdSql) {
        this.editGenresByMovieIdSql = editGenresByMovieIdSql;
    }
}
