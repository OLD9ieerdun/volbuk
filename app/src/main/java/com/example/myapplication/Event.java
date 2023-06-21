package com.example.myapplication;

import androidx.annotation.NonNull;

public class Event {
    public String name, direction, data, responsible, place, description;
    public Integer quantity;

    public Event(String name, String direction, String data, String responsible, String place, String description, Integer quantity) {
        this.name = name;
        this.direction = direction;
        this.data = data;
        this.responsible = responsible;
        this.place = place;
        this.quantity = quantity;
        this.description = description;
    }

    @Override
    public String toString() {
        return "Event{" +
                "name='" + name + '\'' +
                ", direction='" + direction + '\'' +
                ", data='" + data + '\'' +
                ", responsible='" + responsible + '\'' +
                ", place='" + place + '\'' +
                ", description='" + description + '\'' +
                ", quantity=" + quantity +
                '}';
    }

    public Event(){

    }
}
