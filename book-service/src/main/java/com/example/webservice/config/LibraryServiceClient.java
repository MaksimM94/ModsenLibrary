package com.example.webservice.config;

import com.example.webservice.model.dto.BookRecord;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@AllArgsConstructor
public class LibraryServiceClient {
    private final RestTemplate restTemplate;
    public ResponseEntity<BookRecord> sendPost(BookRecord bookRecord) {
        final var url = "http://book-service/records";
        HttpEntity<BookRecord> request = new HttpEntity<>(bookRecord);
        BookRecord res = restTemplate.postForObject(url, request, BookRecord.class);
        return ResponseEntity.ok(res);
    }
}
