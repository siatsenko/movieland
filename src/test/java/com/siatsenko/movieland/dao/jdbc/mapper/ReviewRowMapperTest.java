package com.siatsenko.movieland.dao.jdbc.mapper;

import com.siatsenko.movieland.entity.common.Review;
import org.junit.Test;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ReviewRowMapperTest {

    @Test
    public void mapRow() throws SQLException {
        ResultSet resultSet = mock(ResultSet.class);

        when(resultSet.getInt("id")).thenReturn(1);
        when(resultSet.getString("text")).thenReturn("Гениальное кино!");

        ReviewRowMapper reviewRowMapper = new ReviewRowMapper();
        Review review = reviewRowMapper.mapRow(resultSet, 0);
        assertEquals(1, review.getId());
        assertEquals("Гениальное кино!", review.getText());

    }
}