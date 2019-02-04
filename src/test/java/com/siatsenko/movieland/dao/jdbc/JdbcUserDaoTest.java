package com.siatsenko.movieland.dao.jdbc;

import com.siatsenko.movieland.entity.common.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JdbcUserDaoTest {

    private JdbcUserDao jdbcUserDao;

    @Test
    public void getByIds() {
        List<User> users = jdbcUserDao.getByIds(Arrays.asList(1));
        assertEquals("Рональд Рейнольдс", users.get(0).getName());
        assertEquals("ronald.reynolds66@example.com", users.get(0).getEmail());
    }

    @Test
    public void getByAuth() {
        User user = jdbcUserDao.getByAuth("ronald.reynolds66@example.com", "paco");
        assertEquals("Рональд Рейнольдс", user.getName());
        assertEquals("ronald.reynolds66@example.com", user.getEmail());
    }

    @Autowired
    public void setJdbcUserDao(JdbcUserDao jdbcUserDao) {
        this.jdbcUserDao = jdbcUserDao;
    }

}