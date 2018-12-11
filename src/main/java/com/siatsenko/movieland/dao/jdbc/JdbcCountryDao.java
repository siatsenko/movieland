package com.siatsenko.movieland.dao.jdbc;

import com.siatsenko.movieland.dao.CountryDao;
import com.siatsenko.movieland.dao.jdbc.mapper.CountryRowMapper;
import com.siatsenko.movieland.entity.Country;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JdbcCountryDao implements CountryDao {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    private JdbcTemplate jdbcTemplate;
    private String countriesByMovieIdSql;
    private String allCountriesSql;
    private CountryRowMapper countryRowMapper;

    @Override
    public List<Country> getByMovieId(int movieId) {
        List<Country> countries = jdbcTemplate.query(countriesByMovieIdSql, countryRowMapper, movieId);
        logger.trace("getByMovieId({}) finished and return countries: {}", movieId, countries);
        return countries;
    }

    @Override
    public List<Country> getAll() {
        List<Country> countries = jdbcTemplate.query(allCountriesSql, countryRowMapper);
        logger.trace("getAll finished and return countries: {}", countries);
        return countries;
    }

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Autowired
    public void setCountriesByMovieIdSql(String countriesByMovieIdSql) {
        this.countriesByMovieIdSql = countriesByMovieIdSql;
    }

    @Autowired
    public void setCountryRowMapper(CountryRowMapper countryRowMapper) {
        this.countryRowMapper = countryRowMapper;
    }

    @Autowired
    public void setAllCountriesSql(String allCountriesSql) {
        this.allCountriesSql = allCountriesSql;
    }
}
