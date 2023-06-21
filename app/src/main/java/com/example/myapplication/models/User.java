package com.example.myapplication.models;

public class User {
    public String id, name, secName, email, data, password, post, URLimage;
    public Integer point;
    public Boolean book;

    public User(){

    };

    public User(String id, String name, String secName, String data, String email, String password, String post, Integer point, Boolean book, String URLimage) {
        this.URLimage = URLimage;
        this.id = id;
        this.name = name;
        this.secName = secName;
        this.data = data;
        this.email = email;
        this.password = password;
        this.post = post;
        this.point = point;
        this.book = book;
    }
}