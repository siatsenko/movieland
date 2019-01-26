package com.siatsenko.movieland.web.controller;

import com.siatsenko.movieland.entity.common.Country;
import com.siatsenko.movieland.service.CountryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CountryController {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    private CountryService countryService;

    @GetMapping(path = "/country", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<Country> getAll() {
        logger.info("Sending request to get all countries");
        List<Country> countries = countryService.getAll();
        logger.debug("Returning {} countries", countries.size());
        return countries;
    }

    @Autowired
    public void setCountryService(CountryService countryService) {
        this.countryService = countryService;
    }
}
