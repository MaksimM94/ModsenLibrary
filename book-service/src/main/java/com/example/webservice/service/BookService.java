package com.example.webservice.service;

import com.example.webservice.config.LibraryServiceClient;
import com.example.webservice.exception.BookAlreadyExists;
import com.example.webservice.exception.EmptyBookStorage;
import com.example.webservice.exception.InvalidBookData;
import com.example.webservice.model.dto.BookRecord;
import com.example.webservice.model.entity.Book;
import com.example.webservice.repository.BookJpaRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class BookService {
    private final BookJpaRepository bookJpaRepository;
    private final LibraryServiceClient libraryServiceClient;
    public List<Book> findAllBooks() throws EmptyBookStorage {
        List<Book> books = bookJpaRepository.findAll();
        if (!books.isEmpty())
            return books;
        throw new EmptyBookStorage(EmptyBookStorage.EMPTY_STORAGE);
    }
    public Optional<Book> findBookById(Long id) throws InvalidBookData {
        Book book = bookJpaRepository.findById(id)
                .orElseThrow(()->new InvalidBookData(InvalidBookData.INVALID_BOOK_ID));
        return Optional.of(book);
    }
    public Optional<Book> findByIsbn(String isbn) throws InvalidBookData {
        if (isbn == null || isbn.isEmpty()) throw new InvalidBookData(InvalidBookData.INVALID_BOOK_ISBN);
        Book book = bookJpaRepository.findByIsbn(isbn)
                .orElseThrow(()->new InvalidBookData(InvalidBookData.INVALID_BOOK_ISBN));
        return Optional.of(book);
    }
    public Optional<Book> addBook(Book book) throws BookAlreadyExists {
        if (bookJpaRepository.exists(Example.of(book)))
            throw new BookAlreadyExists(BookAlreadyExists.BOOK_ALREADY_EXISTS);
        System.out.println(book);
        book = bookJpaRepository.save(book);
        libraryServiceClient.sendPost(new BookRecord(book.getId(), null, null));
        System.out.println("Book was succesfully added to the microservice");
        return Optional.of(book);
    }
    public Optional<Book> updateBook(Book book) throws InvalidBookData {
        if (book == null)
            throw new InvalidBookData(InvalidBookData.INVALID_BOOK_ISBN);
        return Optional.of(bookJpaRepository.save(book));
    }
    public void deleteBookById(Long id) throws InvalidBookData {
        if (id == null)
            throw new InvalidBookData(InvalidBookData.INVALID_BOOK_ISBN);
        bookJpaRepository.deleteById(id);
        libraryServiceClient.sendDelete(new BookRecord(id, null, null));
    }
}
