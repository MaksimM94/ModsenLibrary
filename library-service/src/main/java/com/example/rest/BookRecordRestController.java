package com.example.rest;

import com.example.model.BookRecord;
import com.example.service.BookRecordService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class BookRecordRestController {
    private final BookRecordService bookRecordService;
    @GetMapping("/records")
    public List<BookRecord> allFreeBookRecords() {
        return bookRecordService.findAllFreeBookRecords();
    }
    @PostMapping("/records")
    public ResponseEntity<BookRecord> addBookRecords(@RequestBody BookRecord bookRecord) throws Exception {
        return ResponseEntity.of(bookRecordService.addRecord(bookRecord));
    }
    @PatchMapping("/records/{id}")
    public BookRecord updateBookRecord(@PathVariable Long id) {
        return bookRecordService.updateRecord(id).get();
    }
    @DeleteMapping("/records/{id}")
    public void deleteBookRecord(@PathVariable Long id) throws Exception {
        bookRecordService.deleteRecord(id);
    }
}
