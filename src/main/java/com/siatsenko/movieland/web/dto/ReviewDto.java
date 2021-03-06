package com.siatsenko.movieland.web.dto;

import com.siatsenko.movieland.entity.common.Review;

public class ReviewDto {
    private int id;
    private UserDto user;
    private String text;

    public ReviewDto(Review review) {
        this.id = review.getId();
        this.text = review.getText();
        UserDto userDto = new UserDto(review.getUser());
        this.user = userDto;
    }

    public int getId() {
        return id;
    }

    public UserDto getUser() {
        return user;
    }

    public String getText() {
        return text;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }

    public void setText(String text) {
        this.text = text;
    }
}
