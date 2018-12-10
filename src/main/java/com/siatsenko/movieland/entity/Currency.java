package com.siatsenko.movieland.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Currency {
    @JsonProperty("r030")
    private int id;
    @JsonProperty("txt")
    private String name;
    @JsonProperty("cc")
    private String code;
    private double rate;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public double getRate() {
        return rate;
    }

    @Override
    public String toString() {
        return "Currency{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", rate=" + rate +
                '}';
    }
}
