package com.siatsenko.movieland.entity.request;

import com.siatsenko.movieland.entity.common.SortType;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public class RequestParameters {

    LinkedHashMap<String, SortType> sortings;

    public RequestParameters() {
        sortings = new LinkedHashMap<>();
    }

    public Map<String, SortType> getSortings() {
        return sortings;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RequestParameters that = (RequestParameters) o;
        return Objects.equals(sortings, that.sortings);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sortings);
    }

    @Override
    public String toString() {
        return "RequestParameters{" +
                "sortings=" + sortings +
                '}';
    }
}
