package com.yyit.mss.sample02.oauth2.server;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class User {

    private String username;

    private String password;

    private String[] roles;

    public User(String username, String password, String[] roles) {
        this.username = username;
        this.password = password;
        this.roles = roles;
    }

    public String getUsername() {

        return username;
    }

    public void setUsername(String username) {

        this.username = username;
    }

    public String getPassword() {

        return password;
    }

    public void setPassword(String password) {

        this.password = password;
    }

    public String[] getRoles() {

        return roles;
    }

    public void setRoles(String[] roles) {

        this.roles = roles;
    }

    public static void main(String[] args) {
        System.out.println(Base64.getEncoder().encodeToString("yyit:123456".getBytes(StandardCharsets.UTF_8)));
    }
}