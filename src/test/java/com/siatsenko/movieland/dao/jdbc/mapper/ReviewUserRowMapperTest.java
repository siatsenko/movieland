package com.siatsenko.movieland.dao.jdbc.mapper;

import com.siatsenko.movieland.entity.Review;
import org.junit.Test;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ReviewUserRowMapperTest {

    @Test
    public void mapRow() throws SQLException {
        ResultSet resultSet = mock(ResultSet.class);

        when(resultSet.getInt("review_id")).thenReturn(1);
        when(resultSet.getInt("id")).thenReturn(2);
        when(resultSet.getString("name")).thenReturn("Дарлин Эдвардс");

        UserRowMapper userRowMapper = new UserRowMapper();
        ReviewUserRowMapper reviewUserRowMapper = new ReviewUserRowMapper();
        reviewUserRowMapper.setUserRowMapper(userRowMapper);

        Review review = reviewUserRowMapper.mapRow(resultSet, 0);

        assertEquals(review.getId(), 1);
        assertEquals(review.getUser().getId(), 2);
        assertEquals(review.getUser().getName(), "Дарлин Эдвардс");
    }
}