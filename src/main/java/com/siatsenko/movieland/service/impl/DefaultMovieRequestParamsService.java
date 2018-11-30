package com.siatsenko.movieland.service.impl;

import com.siatsenko.movieland.entity.RequestParams;
import com.siatsenko.movieland.entity.SortType;
import com.siatsenko.movieland.service.RequestParamsService;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class DefaultMovieRequestParamsService implements RequestParamsService {

    private static final List<String> ALLOWED_FIELDS = Arrays.asList("rating", "price");

    @Override
    public RequestParams setSortings(RequestParams requestParams, Map<String, String> queryMap) {
        if (queryMap == null) {
            return requestParams;
        }
        LinkedHashMap<String, SortType> sortings = requestParams.getSortings();
        for (String key : queryMap.keySet()) {
            String value = queryMap.get(key);
            if (ALLOWED_FIELDS.contains(key) && SortType.contains(value)) {
                sortings.put(key, SortType.valueOf(value.toUpperCase()));
            }
        }
        return requestParams;
    }

    @Override
    public RequestParams setSortings(Map<String, String> queryMap) {
        RequestParams requestParams = new RequestParams();
        return setSortings(requestParams, queryMap);
    }


}
