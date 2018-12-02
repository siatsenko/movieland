package com.siatsenko.movieland.dao.jdbc.mapper;

import com.siatsenko.movieland.entity.Country;
import org.junit.Test;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CountryRowMapperTest {

    @Test
    public void mapRow() throws SQLException {
        ResultSet resultSet = mock(ResultSet.class);

        when(resultSet.getInt("id")).thenReturn(1);
        when(resultSet.getString("name")).thenReturn("США");

        CountryRowMapper countryRowMapper = new CountryRowMapper();
        Country country = countryRowMapper.mapRow(resultSet, 0);
        assertEquals(country.getId(), 1);
        assertEquals(country.getName(), "США");
    }
}