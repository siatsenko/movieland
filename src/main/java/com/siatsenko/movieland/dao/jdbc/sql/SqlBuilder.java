package com.siatsenko.movieland.dao.jdbc.sql;

import com.siatsenko.movieland.entity.RequestParams;
import com.siatsenko.movieland.entity.SortType;
import org.springframework.stereotype.Repository;

import java.util.LinkedHashMap;
import java.util.StringJoiner;

@Repository
public class SqlBuilder {

    private static final String ORDER_REPLACE = "/*ORDER BY*/";

    public String setOrder(String query, RequestParams requestParams) {
        if (requestParams == null) {
            return query;
        }

        LinkedHashMap<String, SortType> sortings = requestParams.getSortings();

        boolean isEmpty = true;
        StringJoiner stringJoiner = new StringJoiner(", ", "ORDER BY ", "");
        for (String key : sortings.keySet()) {
            String value = sortings.get(key).toString().toLowerCase();
            stringJoiner.add(key + " " + value);
            isEmpty = false;
        }

        return isEmpty ? query : query.replace(ORDER_REPLACE, stringJoiner.toString());
    }

}
