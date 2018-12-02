package com.siatsenko.movieland.dao.jdbc;

import com.siatsenko.movieland.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(value = "/spring/test-context.xml")
public class JdbcUserDaoTest {

    private JdbcUserDao jdbcUserDao;

    @Test
    public void getByReviewId() {
        User user = jdbcUserDao.getByReviewId(3);

        assertEquals(user.getId(), 6);
        assertEquals(user.getName(), "Трэвис Райт");
        assertEquals(user.getEmail(), "travis.wright36@example.com");
        assertEquals(user.getNick(), "smart");

    }

    @Autowired
    public void setJdbcUserDao(JdbcUserDao jdbcUserDao) {
        this.jdbcUserDao = jdbcUserDao;
    }
}