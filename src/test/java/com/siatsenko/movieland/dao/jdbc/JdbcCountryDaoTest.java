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

        assertEquals(2, countries.size());

        Country countryFirst = countries.get(0);
        assertEquals(1, countryFirst.getId());
//        assertEquals("США", countryFirst.getName());

        Country countrySecond = countries.get(1);
        assertEquals(3, countrySecond.getId());
//        assertEquals("Великобритания", countrySecond.getName());
    }

    @Autowired
    public void setJdbcCountryDao(JdbcCountryDao jdbcCountryDao) {
        this.jdbcCountryDao = jdbcCountryDao;
    }
}