package com.siatsenko.movieland.dao.jdbc.mapper;

import com.siatsenko.movieland.entity.common.Genre;
import org.junit.Test;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GenreRowMapperTest {

    @Test
    public void mapRow() throws SQLException {

        ResultSet resultSet = mock(ResultSet.class);

        when(resultSet.getInt("id")).thenReturn(1);
        when(resultSet.getString("name")).thenReturn("драма");

        GenreRowMapper genreRowMapper = new GenreRowMapper();
        Genre genre = genreRowMapper.mapRow(resultSet, 0);
        assertEquals(1, genre.getId());
        assertEquals("драма", genre.getName());

    }

}