package com.siatsenko.movieland.entity;

import java.util.LinkedHashMap;
import java.util.Map;

public class RequestParameters {

    LinkedHashMap<String, SortType> sortings;

    public RequestParameters() {
        sortings = new LinkedHashMap<>();
    }

    public Map<String, SortType> getSortings() {
        return sortings;
    }

}
