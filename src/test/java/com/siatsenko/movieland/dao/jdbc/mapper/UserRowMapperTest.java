package com.siatsenko.movieland.dao.jdbc.mapper;

import com.siatsenko.movieland.entity.common.Role;
import com.siatsenko.movieland.entity.common.User;
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
        when(resultSet.getString("email")).thenReturn("ronald.reynolds66@example.com");
        when(resultSet.getString("role")).thenReturn("ADMIN");

        UserRowMapper userRowMapper = new UserRowMapper();
        User user = userRowMapper.mapRow(resultSet, 0);
        assertEquals(1, user.getId());
        assertEquals("Рональд Рейнольдс", user.getName());
        assertEquals("ronald.reynolds66@example.com", user.getEmail());
        assertEquals(Role.ADMIN, user.getRole());
    }
}