package com.upb.agripos.auth;

public class User {
    private String username;
    private String role; // ADMIN atau KASIR

    public User(String username, String role) {
        this.username = username;
        this.role = role;
    }

    public String getUsername() { return username; }
    public String getRole() { return role; }
}