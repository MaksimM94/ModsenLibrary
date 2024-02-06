package com.example.webservice.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class BookServiceExceptionHandler extends ResponseEntityExceptionHandler {
    public BookServiceExceptionHandler() {
        super();
    }
    @ExceptionHandler({BookAlreadyExists.class})
    public ResponseEntity<Object> bookExists(final RuntimeException ex, final WebRequest request) {
        logger.error("500 Status Code", ex);
        return handleExceptionInternal(ex, BookAlreadyExists.BOOK_ALREADY_EXISTS, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
    }
    @ExceptionHandler({EmptyBookStorage.class})
    public ResponseEntity<Object> emptyStorage(final RuntimeException ex, final WebRequest request) {
        logger.error("500 Status Code", ex);
        return handleExceptionInternal(ex, EmptyBookStorage.EMPTY_STORAGE, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
    }
    @ExceptionHandler({InvalidBookData.class})
    public ResponseEntity<Object> invalidBookData(final RuntimeException ex, final WebRequest request) {
        logger.error("500 Status Code", ex);
        return handleExceptionInternal(ex, InvalidBookData.INVALID_BOOK_ISBN, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
    }

}
