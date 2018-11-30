package com.siatsenko.movieland.service;


import com.siatsenko.movieland.entity.RequestParams;

import java.util.Map;

public interface RequestParamsService {

    RequestParams setSortings(RequestParams requestParams, Map<String, String> queryMap);

    RequestParams setSortings(Map<String, String> queryMap);

}
