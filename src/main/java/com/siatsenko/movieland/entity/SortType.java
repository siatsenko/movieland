package com.siatsenko.movieland.entity;

import java.util.Arrays;
import java.util.List;

public enum SortType {
    ASC,
    DESC;

    private static List sortTypes = Arrays.asList(SortType.values());

    public static boolean isValid(String value) {
        boolean result = false;
        List sortTypes = Arrays.asList(SortType.values());
        try {
            result = sortTypes.contains(SortType.valueOf(value.toUpperCase()));
        } catch (IllegalArgumentException e) {
            return false;
        }
        return result;
    }

}
