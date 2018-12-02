package com.siatsenko.movieland.dao.jdbc.mapper;

import com.siatsenko.movieland.entity.Movie;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class MovieRowMapper implements RowMapper<Movie> {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public Movie mapRow(ResultSet resultSet, int rowId) throws SQLException {
        Movie movie = new Movie();
        movie.setId(resultSet.getInt("id"));
        movie.setNameRussian(resultSet.getString("name_russian"));
        movie.setNameNative(resultSet.getString("name_native"));
        movie.setYearOfRelease(resultSet.getString("year_of_release"));
        movie.setDescription(resultSet.getString("description"));
        movie.setRating(resultSet.getDouble("rating"));
        movie.setPrice(resultSet.getDouble("price"));
        movie.setPicturePath(resultSet.getString("picture_path"));

        logger.trace("mapRow finished and return movie: {}", movie);
        return movie;
    }
}
