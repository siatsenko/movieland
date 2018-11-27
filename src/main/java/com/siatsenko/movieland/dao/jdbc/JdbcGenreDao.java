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
    private String allGenreSql;
    private GenreRowMapper genreRowMapper;

    @Override
    public List<Genre> getAll() {
        List<Genre> genres = jdbcTemplate.query(allGenreSql, genreRowMapper);
        logger.trace("getAll finished and return genres: {}", genres);
        return genres;
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
