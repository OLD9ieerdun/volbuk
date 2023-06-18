package com.example.myapplication.models;

public class ApplicationEvent {
    public String id, eventName, userName;

    public ApplicationEvent(){

    }

    public ApplicationEvent(String id, String eventName, String userName) {
        this.id = id;
        this.eventName = eventName;
        this.userName = userName;
    }
}
