package com.siatsenko.movieland.dao.jdbc.sql;

import com.siatsenko.movieland.entity.request.RequestParameters;
import com.siatsenko.movieland.entity.common.SortType;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.StringJoiner;

@Repository
public class SqlBuilder {

    private static final String ORDER_REPLACE = "/*ORDER BY*/";

    public String setOrder(String query, RequestParameters requestParameters) {
        if (requestParameters == null) {
            return query;
        }

        Map<String, SortType> sortings = requestParameters.getSortings();

        boolean isEmpty = true;
        StringJoiner stringJoiner = new StringJoiner(", ", "ORDER BY ", "");

        for (Map.Entry<String, SortType> sortingEntry : sortings.entrySet()) {
            String key = sortingEntry.getKey();
            String value = sortingEntry.getValue().toString().toLowerCase();
            stringJoiner.add(key + " " + value);
            isEmpty = false;
        }

        return isEmpty ? query : query.replace(ORDER_REPLACE, stringJoiner.toString());
    }

}
