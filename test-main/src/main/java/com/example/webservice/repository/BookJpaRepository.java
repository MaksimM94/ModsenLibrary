package com.example.webservice.repository;

import com.example.webservice.model.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface BookJpaRepository extends JpaRepository<Book, Long> {
    @Query(value = "select b.* from books b where b.isbn = :isbn", nativeQuery = true)
    Optional<Book> findByIsbn(@Param("isbn") String isbn);

}
