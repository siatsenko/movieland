package com.siatsenko.movieland.dao.jdbc;

import com.siatsenko.movieland.dao.UserDao;
import com.siatsenko.movieland.dao.jdbc.mapper.UserDetailRowMapper;
import com.siatsenko.movieland.dao.jdbc.mapper.UserRowMapper;
import com.siatsenko.movieland.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JdbcUserDao implements UserDao {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    private JdbcTemplate jdbcTemplate;
    private String usersByIdsSql;
    private String userByAuthSql;
    private UserRowMapper userRowMapper;
    private UserDetailRowMapper userDetailRowMapper;

    @Override
    public List<User> getByIds(List<Integer> userIds) {
        int[] idList = userIds.stream().mapToInt(Integer::intValue).toArray();
        List<User> users = jdbcTemplate.query(usersByIdsSql, userRowMapper, idList);
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
    public void setUsersByIdsSql(String usersByIdsSql) {
        this.usersByIdsSql = usersByIdsSql;
    }

    @Autowired
    public void setUserByAuthSql(String userByAuthSql) {
        this.userByAuthSql = userByAuthSql;
    }

    @Autowired
    public void setUserDetailRowMapper(UserDetailRowMapper userDetailRowMapper) {
        this.userDetailRowMapper = userDetailRowMapper;
    }
}
