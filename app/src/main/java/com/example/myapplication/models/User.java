package com.example.myapplication.models;

public class User {
    public String name, secName, email, data, post;
    public Boolean book;
    public Integer point;

    public User(String name, String secName, String email, String data, String post, Boolean book, Integer point) {
        this.name = name;
        this.secName = secName;
        this.email = email;
        this.data = data;
        this.post = post;
        this.book = book;
        this.point = point;
    }
}