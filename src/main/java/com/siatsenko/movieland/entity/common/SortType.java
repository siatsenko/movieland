package com.siatsenko.movieland.entity.common;

import java.util.Arrays;
import java.util.List;

public enum SortType {
    ASC,
    DESC;

    private static List sortTypes = Arrays.asList(SortType.values());

    public static boolean contains(String value) {
        boolean result = false;
        try {
            result = sortTypes.contains(SortType.valueOf(value.toUpperCase()));
        } catch (IllegalArgumentException e) {
            return false;
        }
        return result;
    }

}
