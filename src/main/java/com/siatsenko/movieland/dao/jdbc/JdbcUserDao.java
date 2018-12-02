package com.siatsenko.movieland.dao.jdbc;

import com.siatsenko.movieland.dao.UserDao;
import com.siatsenko.movieland.dao.jdbc.mapper.UserRowMapper;
import com.siatsenko.movieland.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JdbcUserDao implements UserDao {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    private JdbcTemplate jdbcTemplate;
    private String usersByReviewIdSql;
    private UserRowMapper userRowMapper;

    @Override
    public User getByReviewId(int reviewId) {
        List<User> users = jdbcTemplate.query(usersByReviewIdSql, userRowMapper, reviewId);
        User user = null;
        if (users.size() > 0) {
            user = users.get(0);
        }
        logger.trace("getByReviewId({}) finished and return users: {}", reviewId, user);
        return user;
    }

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Autowired
    public void setUsersByReviewIdSql(String usersByReviewIdSql) {
        this.usersByReviewIdSql = usersByReviewIdSql;
    }

    @Autowired
    public void setUserRowMapper(UserRowMapper userRowMapper) {
        this.userRowMapper = userRowMapper;
    }
}
