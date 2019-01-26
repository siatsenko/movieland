package com.siatsenko.movieland.dao.jdbc.mapper;

import com.siatsenko.movieland.entity.common.Movie;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class MovieDetailRowMapper implements RowMapper<Movie> {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    MovieRowMapper movieRowMapper;

    @Override
    public Movie mapRow(ResultSet resultSet, int i) throws SQLException {
        Movie movie = movieRowMapper.mapRow(resultSet, i);
        movie.setDescription(resultSet.getString("description"));
        logger.trace("mapRow finished and return movie: {}", movie);
        return movie;
    }

    @Autowired
    public void setMovieRowMapper(MovieRowMapper movieRowMapper) {
        this.movieRowMapper = movieRowMapper;
    }
}
