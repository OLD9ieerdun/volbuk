package com.example.myapplication.models;

public class User {
    public String id, name, secName, email, data, password, post;
    public Integer point;

    public User(){

    };

    public User(String id, String name, String secName, String post, String email, String password, String data, Integer point) {
        this.id = id;
        this.name = name;
        this.secName = secName;
        this.password = password;
        this.email = email;
        this.data = data;
        this.post = post;
        //this.book = book;
        this.point = point;
    }
}