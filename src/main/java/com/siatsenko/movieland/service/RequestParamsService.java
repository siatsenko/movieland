package com.siatsenko.movieland.service;

import com.siatsenko.movieland.entity.RequestParameters;

import java.util.Map;

public interface RequestParamsService {

    RequestParameters setSortings(RequestParameters requestParameters, Map<String, String> queryMap);

    RequestParameters setSortings(Map<String, String> queryMap);

}
