package com.example.webservice.exception;

public class BookAlreadyExists extends RuntimeException {
    public static final String BOOK_ALREADY_EXISTS = "Книга уже добавлена!";
    public BookAlreadyExists(String message) {
        super(message);
    }
}
