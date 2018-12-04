package com.siatsenko.movieland.web.dto;

import com.siatsenko.movieland.entity.Country;

public class CountryDto {
    private int id;
    private String name;

    public CountryDto(Country country) {
        this.id = country.getId();
        this.name = country.getName();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
