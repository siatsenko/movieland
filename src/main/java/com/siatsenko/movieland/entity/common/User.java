package com.siatsenko.movieland.entity.common;

import java.util.Objects;

public class User {
    private int id;
    private String name;
    private String email;
    private Role role;

    public User() {
    }

    public User(int id) {
        this.id = id;
    }

    public User(int id, String name, String email, Role role) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.role = role;
    }

    public User(int id, String name, Role role) {
        this.id = id;
        this.name = name;
        this.role = role;
    }

    public User(String name, String email, Role role) {
        this.name = name;
        this.email = email;
        this.role = role;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id &&
                name.equals(user.name) &&
                email.equals(user.email) &&
                role == user.role;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, email, role);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", role=" + role +
                '}';
    }
}
