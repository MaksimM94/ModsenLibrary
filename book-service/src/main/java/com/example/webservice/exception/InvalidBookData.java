package com.example.webservice.exception;

public class InvalidBookData extends Exception{
    public static final String INVALID_BOOK_ID = "Книги с таким id не существует";
    public static final String INVALID_BOOK_ISBN = "Книги с таким ISBN не существует";
    public InvalidBookData(String message) {
        super(message);
    }
}
