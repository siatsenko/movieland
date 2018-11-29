package com.siatsenko.movieland.dao.jdbc.sql;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(value = "/spring/test-context.xml")
public class SqlBuilderTest {

    private SqlBuilder sqlBuilder;

    static final String QUERY = "SELECT * FROM v_movies /*ORDER BY*/";

    @Test
    public void setOrder() {

        assertEquals(sqlBuilder.setOrder(QUERY, "rating asc"), "SELECT * FROM v_movies ORDER BY rating asc");
        assertEquals(sqlBuilder.setOrder(QUERY, "price asc, rating desc"), "SELECT * FROM v_movies ORDER BY price asc, rating desc");
        assertEquals(sqlBuilder.setOrder(QUERY, "rating desc"), "SELECT * FROM v_movies ORDER BY rating desc");
        assertEquals(sqlBuilder.setOrder(QUERY, null), QUERY);

    }

    @Autowired
    public void setSqlBuilder(SqlBuilder sqlBuilder) {
        this.sqlBuilder = sqlBuilder;
    }
}