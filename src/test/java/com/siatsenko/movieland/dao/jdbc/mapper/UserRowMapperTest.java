package com.siatsenko.movieland.dao.jdbc.mapper;

import com.siatsenko.movieland.entity.User;
import org.junit.Test;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UserRowMapperTest {

    @Test
    public void mapRow() throws SQLException {
        ResultSet resultSet = mock(ResultSet.class);

        when(resultSet.getInt("id")).thenReturn(1);
        when(resultSet.getString("name")).thenReturn("Рональд Рейнольдс");
        UserRowMapper userRowMapper = new UserRowMapper();
        User user = userRowMapper.mapRow(resultSet, 0);
        assertEquals(user.getId(), 1);
        assertEquals(user.getName(), "Рональд Рейнольдс");
    }
}