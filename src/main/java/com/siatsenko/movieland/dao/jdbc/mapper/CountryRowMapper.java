package com.siatsenko.movieland.dao.jdbc.mapper;

import com.siatsenko.movieland.entity.common.Country;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class CountryRowMapper implements RowMapper<Country> {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public Country mapRow(ResultSet resultSet, int i) throws SQLException {
        int id = resultSet.getInt("id");
        String name = resultSet.getString("name");
        Country country = new Country(id, name);

        logger.trace("mapRow finished and return country: {}", country);
        return country;
    }

}
