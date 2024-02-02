package com.example.repository;

import com.example.model.BookRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookRecordJpaRepository extends JpaRepository<BookRecord, Long> {
    @Query(value = " select b.* from books_records b where b.released_at <= (select now()) or b.released_at is null;", nativeQuery = true)
    List<BookRecord> findFreeBooks();
}
