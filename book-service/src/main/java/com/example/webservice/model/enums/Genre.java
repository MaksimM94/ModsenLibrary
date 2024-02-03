package com.example.webservice.model.enums;

public enum Genre {
    ADVENTURE("Adventure"),
    DETECTIVE("Detective"),
    ROMANCE("Romance");
    private final String name;
    Genre(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
