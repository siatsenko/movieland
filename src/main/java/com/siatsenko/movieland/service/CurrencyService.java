package com.siatsenko.movieland.service;

import com.siatsenko.movieland.entity.Currency;
import com.siatsenko.movieland.entity.Movie;

public interface CurrencyService {

    Movie enrich(Movie movie,String currencyCode);

}
