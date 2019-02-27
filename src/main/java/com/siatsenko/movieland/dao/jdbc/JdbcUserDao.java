package com.siatsenko.movieland.dao.jdbc;

import com.siatsenko.movieland.dao.UserDao;
import com.siatsenko.movieland.dao.jdbc.mapper.UserDetailRowMapper;
import com.siatsenko.movieland.dao.jdbc.mapper.UserRowMapper;
import com.siatsenko.movieland.entity.common.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JdbcUserDao implements UserDao {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private UserRowMapper userRowMapper;
    private UserDetailRowMapper userDetailRowMapper;

    @Value("${queries.users.usersByIdsSql}")
    private String usersByIdsSql;
    @Value("${queries.users.userByAuthSql}")
    private String userByAuthSql;

    @Override
    public List<User> getByIds(List<Integer> userIds) {
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("ids", userIds);
        List<User> users = namedParameterJdbcTemplate.query(usersByIdsSql, parameters, userRowMapper);
        logger.debug("getByIds({}) finished and return users:", userIds, users);
        return users;
    }

    @Override
    public User getByAuth(String email, String password) {
        User result = null;
        try {
            User user = jdbcTemplate.queryForObject(userByAuthSql, userDetailRowMapper, email, password);
            result = user;
        } catch (EmptyResultDataAccessException e) {
            logger.debug("getByAuth({}) user not found", email);
        }
        logger.debug("getByAuth({}) finished and return user:", email, result);
        return result;
    }

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Autowired
    public void setUserRowMapper(UserRowMapper userRowMapper) {
        this.userRowMapper = userRowMapper;
    }

    @Autowired
    public void setUserDetailRowMapper(UserDetailRowMapper userDetailRowMapper) {
        this.userDetailRowMapper = userDetailRowMapper;
    }

    @Autowired
    public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }
}
