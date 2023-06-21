package com.example.myapplication;

import java.net.URL;

public class User {
    public String name, secName, email, data, post, URLimage;
    public Boolean book;
    public Integer point;

    public User(String name, String secName, String email, String data, String post, Boolean book, Integer point, String URLimage) {
        this.URLimage = URLimage;
        this.name = name;
        this.secName = secName;
        this.email = email;
        this.data = data;
        this.post = post;
        this.book = book;
        this.point = point;
    }
}