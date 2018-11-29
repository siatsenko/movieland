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

    static final Map<String, String> paramsRating = new HashMap<String, String>() {{
        put("rating", "asc");
    }};
    static final Map<String, String> paramsPrice = new HashMap<String, String>() {{
        put("price", "desc");
    }};
    static final Map<String, String> paramsBoth = new HashMap<String, String>() {{
        put("price", "asc");
        put("rating", "desc");
    }};
    static final Map<String, String> paramsWrongField = new HashMap<String, String>() {{
        put("ratings", "desc");
        put("price", "asc");
    }};
    static final Map<String, String> paramsWrongSort = new HashMap<String, String>() {{
        put("rating", "desc");
        put("price", "ascc");
    }};
    static final Map<String, String> paramsWrongBoth = new HashMap<String, String>() {{
        put("ratings", "desc");
        put("price", "ascc");
    }};

    static final String QUERY = "SELECT * FROM v_movies /*ORDER BY*/";

    @Test
    public void setOrder() {

        System.out.println(sqlBuilder);
        paramsRating.toString();

        assertEquals(sqlBuilder.setOrder(QUERY,paramsRating), "SELECT * FROM v_movies ORDER BY rating asc");
        assertEquals(sqlBuilder.setOrder(QUERY,paramsPrice), "SELECT * FROM v_movies ORDER BY price desc");
        assertEquals(sqlBuilder.setOrder(QUERY,paramsBoth), "SELECT * FROM v_movies ORDER BY price asc, rating desc");
        assertEquals(sqlBuilder.setOrder(QUERY,paramsWrongField), "SELECT * FROM v_movies ORDER BY price asc");
        assertEquals(sqlBuilder.setOrder(QUERY,paramsWrongSort), "SELECT * FROM v_movies ORDER BY rating desc");
        assertEquals(sqlBuilder.setOrder(QUERY,paramsWrongBoth), QUERY);
        assertEquals(sqlBuilder.setOrder(QUERY,null), QUERY);

    }

    @Autowired
    public void setSqlBuilder(SqlBuilder sqlBuilder) {
        this.sqlBuilder = sqlBuilder;
    }
}