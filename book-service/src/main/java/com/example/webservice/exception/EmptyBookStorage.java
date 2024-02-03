package com.example.webservice.exception;

public class EmptyBookStorage extends Exception {
    public final static String EMPTY_STORAGE = "Хранилище книг пусто";
    public EmptyBookStorage(String message) {
        super(message);
    }
}
