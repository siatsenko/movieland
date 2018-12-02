package com.siatsenko.movieland.dao.jdbc;

import com.siatsenko.movieland.entity.Country;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(value = "/spring/test-context.xml")
public class JdbcCountryDaoTest {

    private JdbcCountryDao jdbcCountryDao;

    @Test
    public void getByMovieId() {
        List<Country> countries = jdbcCountryDao.getByMovieId(4);

        assertEquals(countries.size(), 2);

        Country countryFirst = countries.get(0);
        assertEquals(countryFirst.getId(), 1);
        assertEquals(countryFirst.getName(), "США");

        Country countrySecond = countries.get(1);
        assertEquals(countrySecond.getId(), 3);
        assertEquals(countrySecond.getName(), "Великобритания");
    }

    @Autowired
    public void setJdbcCountryDao(JdbcCountryDao jdbcCountryDao) {
        this.jdbcCountryDao = jdbcCountryDao;
    }
}