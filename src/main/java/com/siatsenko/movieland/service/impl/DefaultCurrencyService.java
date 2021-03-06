package com.siatsenko.movieland.service.impl;

import com.siatsenko.movieland.entity.common.Currency;
import com.siatsenko.movieland.entity.common.Movie;
import com.siatsenko.movieland.service.CurrencyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class DefaultCurrencyService implements CurrencyService {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Value("${currency.url}")
    private String url;

    @Value("${currency.datePattern}")
    private String datePattern;

    private RestTemplate restTemplate;

    private volatile Map<String, Double> cacheRates;

    private Map<String, Double> getRates() {
        logger.debug("getRates: start");
        Map<String, Double> result = new HashMap<>();

        String uri = UriComponentsBuilder.fromHttpUrl(url).queryParam("json").toUriString();
        String dateParam = LocalDateTime.now().format(DateTimeFormatter.ofPattern(datePattern));
        Currency[] currencies = restTemplate.getForObject(uri, Currency[].class, dateParam);

        for (Currency currency : currencies) {
            String key = currency.getCode();
            double value = currency.getRate();
            result.put(key, value);
        }
        logger.trace("getRatesh: finished and return result: ", result);
        return result;
    }

    private double getRate(String currencyCode) {
        if (cacheRates.keySet().contains(currencyCode)) {
            return cacheRates.get(currencyCode);
        }
        throw new IllegalArgumentException("Currency [" + currencyCode + "] not found");
    }

    @Override
    public Movie enrich(Movie movie, String currencyCode) {
        logger.debug("enrich: start");
        double price = movie.getPrice() / getRate(currencyCode);
        movie.setPrice(price);
        logger.trace("enrich: finished and return movie: ", movie);
        return movie;
    }

    @PostConstruct
    @Scheduled(cron = "${scheduled.currency.cron:0 0 * * * *}") // every hour by default
    public Map<String, Double> refresh() {
        logger.debug("refresh: start");
        Map<String, Double> result = getRates();
        cacheRates = result;
        logger.trace("refresh: finished and return result: ", result);
        return result;
    }

    @Autowired
    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

}
