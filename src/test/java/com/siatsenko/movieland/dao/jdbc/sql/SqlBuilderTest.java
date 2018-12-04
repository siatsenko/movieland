package com.siatsenko.movieland.dao.jdbc.sql;

import com.siatsenko.movieland.service.RequestParamsService;
import com.siatsenko.movieland.service.impl.DefaultMovieRequestParamsService;
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

    private RequestParamsService requestParamsService;

    private SqlBuilder sqlBuilder;

    static final String QUERY = "SELECT * FROM v_movies /*ORDER BY*/";

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

    @Test
    public void setOrder() {
        RequestParamsService requestParamsService = new DefaultMovieRequestParamsService();

        assertEquals(sqlBuilder.setOrder(QUERY, requestParamsService.setSortings(paramsRating)), "SELECT * FROM v_movies ORDER BY rating asc");
        assertEquals(sqlBuilder.setOrder(QUERY, requestParamsService.setSortings(paramsPrice)), "SELECT * FROM v_movies ORDER BY price desc");
        assertEquals(sqlBuilder.setOrder(QUERY, requestParamsService.setSortings(paramsBoth)), "SELECT * FROM v_movies ORDER BY price asc, rating desc");
        assertEquals(sqlBuilder.setOrder(QUERY, requestParamsService.setSortings(paramsWrongField)), "SELECT * FROM v_movies ORDER BY price asc");
        assertEquals(sqlBuilder.setOrder(QUERY, requestParamsService.setSortings(paramsWrongSort)), "SELECT * FROM v_movies ORDER BY rating desc");
        assertEquals(sqlBuilder.setOrder(QUERY, requestParamsService.setSortings(paramsWrongBoth)), "SELECT * FROM v_movies /*ORDER BY*/");
    }

    @Autowired
    public void setSqlBuilder(SqlBuilder sqlBuilder) {
        this.sqlBuilder = sqlBuilder;
    }

    @Autowired
    public void setRequestParamsService(RequestParamsService requestParamsService) {
        this.requestParamsService = requestParamsService;
    }
}