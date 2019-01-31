package com.siatsenko.movieland.web.dto;

import com.siatsenko.movieland.entity.common.Genre;

public class GenreDto {
    private int id;
    private String name;

    public GenreDto(Genre genre) {
        this.id = genre.getId();
        this.name = genre.getName();
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
