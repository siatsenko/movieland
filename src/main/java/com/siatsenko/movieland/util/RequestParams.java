package com.siatsenko.movieland.util;

import com.siatsenko.movieland.entity.SortType;

import java.util.*;

public final class RequestParams {

    private static final List<String> ALLOWED_FIELDS = Arrays.asList("rating", "price");

    public static String getOrder(Map<String, String> map) {
        if (map == null) {
            return null;
        }
        boolean isValid = false;
        StringJoiner stringJoiner = new StringJoiner(", ");
        for (String key : map.keySet()) {
            String value = map.get(key);
            if (ALLOWED_FIELDS.contains(key) && SortType.contains(value)) {
                stringJoiner.add(key + " " + value);
                isValid = true;
            }
        }
        return isValid ? stringJoiner.toString() : null;
    }
}
