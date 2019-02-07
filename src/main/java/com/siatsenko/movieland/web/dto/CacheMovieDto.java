package com.siatsenko.movieland.web.dto;

import com.siatsenko.movieland.entity.common.Movie;

public class CacheMovieDto {
    private int cacheId;
    private int id;
    private String nameRussian;

    public CacheMovieDto(int cacheId, Movie movie) {
        this.cacheId = cacheId;
        if (movie != null) {
            this.id = movie.getId();
            this.nameRussian = movie.getNameRussian();
        }
    }

    public int getCacheId() {
        return cacheId;
    }

    public int getId() {
        return id;
    }

    public String getNameRussian() {
        return nameRussian;
    }

    @Override
    public String toString() {
        return "CacheMovieDto{" +
                "cacheId=" + cacheId +
                ", id=" + id +
                ", nameRussian='" + nameRussian + '\'' +
                '}';
    }
}
