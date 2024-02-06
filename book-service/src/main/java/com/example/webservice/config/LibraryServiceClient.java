package com.example.webservice.config;

import com.example.webservice.model.dto.BookRecord;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Component
@AllArgsConstructor
public class LibraryServiceClient {
    private final RestTemplate restTemplate;
    public ResponseEntity<BookRecord> sendPost(BookRecord bookRecord) {
        final var url = "http://proxy/library-service/records";
        HttpEntity<BookRecord> request = new HttpEntity<>(bookRecord);
        BookRecord res = restTemplate.postForObject(url, request, BookRecord.class);
        return ResponseEntity.ok(res);
    }
    public void sendDelete(BookRecord bookRecord) {
        final var url = "http://proxy/library-service/records/{id}";
        Map<String, Long> params = new HashMap<>();
        params.put("id", bookRecord.getBook_id());
        restTemplate.delete(url, params);
    }
}
