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
    private String usersByIdsSql;
    private UserRowMapper userRowMapper;

    @Override
    public List <User> getByIds(List<Integer> userIds) {
        int[] idList = userIds.stream().mapToInt(Integer::intValue).toArray();
        List <User> users = jdbcTemplate.query(usersByIdsSql, userRowMapper, idList);
        logger.debug("getByIds({}) finished and return users: {}", userIds, users);
        return users;
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
}
