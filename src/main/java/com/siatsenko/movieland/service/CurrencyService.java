package com.siatsenko.movieland.service;

import com.siatsenko.movieland.entity.common.Movie;

public interface CurrencyService {

    Movie enrich(Movie movie, String currencyCode);

}
