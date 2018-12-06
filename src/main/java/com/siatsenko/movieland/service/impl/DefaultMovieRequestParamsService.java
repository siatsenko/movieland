package com.siatsenko.movieland.service.impl;

import com.siatsenko.movieland.entity.RequestParameters;
import com.siatsenko.movieland.entity.SortType;
import com.siatsenko.movieland.service.RequestParamsService;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class DefaultMovieRequestParamsService implements RequestParamsService {

    private static final List<String> ALLOWED_FIELDS = Arrays.asList("rating", "price");

    @Override
    public RequestParameters setSortings(RequestParameters requestParameters, Map<String, String> queryMap) {
        if (queryMap == null) {
            return requestParameters;
        }
        Map<String, SortType> sortings = requestParameters.getSortings();

        for (Map.Entry<String, String> queryEntry : queryMap.entrySet()) {
            String key = queryEntry.getKey();
            String value = queryEntry.getValue();
            if (ALLOWED_FIELDS.contains(key) && SortType.contains(value)) {
                sortings.put(key, SortType.valueOf(value.toUpperCase()));
            }
        }
        return requestParameters;
    }

    @Override
    public RequestParameters setSortings(Map<String, String> queryMap) {
        RequestParameters requestParameters = new RequestParameters();
        return setSortings(requestParameters, queryMap);
    }

}
