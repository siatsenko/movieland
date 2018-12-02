package com.siatsenko.movieland.entity;

public class User {
    private int id;
    private String name;
    private String email;
    private String nick;

    public User(int id, String name, String email, String nick) {
        this.id = id;
        this.name = new String(name);
        this.email = new String(email);
        this.nick = new String(nick);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getNick() {
        return nick;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", nick='" + nick + '\'' +
                '}';
    }
}
