package com.siatsenko.movieland.web.dto;

public class LoginUserDto {
    private String uuid;
    private String nickname;

    public LoginUserDto(String uuid, String nickname) {
        this.uuid = uuid;
        this.nickname = nickname;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
