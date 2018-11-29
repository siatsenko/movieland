package com.siatsenko.movieland.util;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class RequestParamsTest {

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
    public void getOrder() {

        assertEquals(RequestParams.getOrder(paramsRating), "rating asc");
        assertEquals(RequestParams.getOrder(paramsPrice), "price desc");
        assertEquals(RequestParams.getOrder(paramsBoth), "price asc, rating desc");
        assertEquals(RequestParams.getOrder(paramsWrongField), "price asc");
        assertEquals(RequestParams.getOrder(paramsWrongSort), "rating desc");
        assertNull(RequestParams.getOrder(paramsWrongBoth));
        assertNull(RequestParams.getOrder(null));

    }
}