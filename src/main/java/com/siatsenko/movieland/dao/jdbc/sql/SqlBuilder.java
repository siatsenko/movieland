package com.siatsenko.movieland.dao.jdbc.sql;

import org.springframework.stereotype.Repository;

@Repository
public class SqlBuilder {

    private static final String ORDER_REPLACE = "/*ORDER BY*/";

    public String setOrder(String query, String order) {
        if (order == null) {
            return query;
        }
        return query.replace(ORDER_REPLACE, "ORDER BY " + order);
    }

}
