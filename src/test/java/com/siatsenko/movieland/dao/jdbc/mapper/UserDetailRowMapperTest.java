package com.siatsenko.movieland.dao.jdbc.mapper;

import com.siatsenko.movieland.entity.User;
import org.junit.Test;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UserDetailRowMapperTest {

    @Test
    public void mapRow() throws SQLException {
        ResultSet resultSet = mock(ResultSet.class);

        when(resultSet.getInt("id")).thenReturn(1);
        when(resultSet.getString("name")).thenReturn("Рональд Рейнольдс");
        when(resultSet.getString("email")).thenReturn("ronald.reynolds66@example.com");
        when(resultSet.getString("role")).thenReturn("ADMIN");
        UserRowMapper userRowMapper = new UserRowMapper();
        UserDetailRowMapper userDetailRowMapper = new UserDetailRowMapper();
        userDetailRowMapper.setUserRowMapper(userRowMapper);

        User user = userDetailRowMapper.mapRow(resultSet, 0);
        assertEquals(1, user.getId());
        assertEquals("Рональд Рейнольдс", user.getName());
        assertEquals("ronald.reynolds66@example.com",user.getEmail());
    }
}