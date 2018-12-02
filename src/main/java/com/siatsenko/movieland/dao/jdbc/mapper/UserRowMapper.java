package com.siatsenko.movieland.dao.jdbc.mapper;

import com.siatsenko.movieland.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class UserRowMapper implements RowMapper<User> {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public User mapRow(ResultSet resultSet, int i) throws SQLException {
        int id = resultSet.getInt("id");
        String name = resultSet.getString("name");
        String email = resultSet.getString("email");
        String nick = resultSet.getString("nick");
        User user = new User(id, name, email, nick);

        logger.trace("mapRow finished and return user: {}", user);
        return user;
    }

}
