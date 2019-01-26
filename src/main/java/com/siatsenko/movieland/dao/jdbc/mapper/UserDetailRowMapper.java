package com.siatsenko.movieland.dao.jdbc.mapper;

import com.siatsenko.movieland.entity.common.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class UserDetailRowMapper implements RowMapper<User> {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    private UserRowMapper userRowMapper;

    @Override
    public User mapRow(ResultSet resultSet, int i) throws SQLException {
        User user = userRowMapper.mapRow(resultSet, i);
        String email = resultSet.getString("email");
        user.setEmail(email);

        logger.trace("mapRow finished and return user: {}", user);
        return user;
    }

    @Autowired
    public void setUserRowMapper(UserRowMapper userRowMapper) {
        this.userRowMapper = userRowMapper;
    }
}
