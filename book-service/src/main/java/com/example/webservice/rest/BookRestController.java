package com.example.webservice.rest;

import com.example.webservice.exception.BookAlreadyExists;
import com.example.webservice.exception.EmptyBookStorage;
import com.example.webservice.exception.InvalidBookData;
import com.example.webservice.model.entity.Book;
import com.example.webservice.service.BookService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class BookRestController {
    private final BookService bookService;
    @GetMapping("/books")
    public List<Book> allBooks() throws EmptyBookStorage {
        return bookService.findAllBooks();
    }
    @GetMapping("/books/{id}")
    public Book bookById(@PathVariable Long id) throws InvalidBookData {
        return bookService.findBookById(id).get();
    }
    @GetMapping("/books/isbn/{isbn}")
    public Book bookByIsbn(@PathVariable String isbn) throws InvalidBookData {
        return bookService.findByIsbn(isbn).get();
    }
    @PostMapping("/books")
    public Book createBook(@RequestBody Book book) throws BookAlreadyExists {
        return bookService.addBook(book).get();
    }
    @PatchMapping("/books/{id}")
    public Book updateBook(@RequestBody Book book) throws InvalidBookData {
        return bookService.updateBook(book).get();
    }
    @DeleteMapping("/books/{id}")
    public void deleteBook(@PathVariable Long id) throws InvalidBookData {
        bookService.deleteBookById(id);
    }
}
