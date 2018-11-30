package com.siatsenko.movieland.entity;

import java.util.LinkedHashMap;

public class RequestParams {

    LinkedHashMap<String, SortType> sortings;

    public RequestParams() {
        sortings = new LinkedHashMap<String, SortType>();
    }

    public LinkedHashMap<String, SortType> getSortings() {
        return sortings;
    }

    public void setSortings(LinkedHashMap<String, SortType> sortings) {
        this.sortings = sortings;
    }
}
