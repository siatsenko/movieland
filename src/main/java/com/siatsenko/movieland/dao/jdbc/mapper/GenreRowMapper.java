package com.siatsenko.movieland.dao.jdbc.mapper;

import com.siatsenko.movieland.entity.Genre;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class GenreRowMapper implements RowMapper<Genre> {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public Genre mapRow(ResultSet resultSet, int i) throws SQLException {
        int id = resultSet.getInt("id");
        String name = resultSet.getString("name");
        Genre genre = new Genre(id, name);

        logger.trace("mapRow finished and return genre: {}", genre);
        return genre;
    }
}
