package com.example.webservice.config;

import com.example.webservice.model.dto.BookRecord;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@AllArgsConstructor
public class LibraryServiceClient {
    private final RestTemplate restTemplate;
    public ResponseEntity<BookRecord> sendPost(Long id) {
        return restTemplate.postForEntity("http://library-service/records", id, BookRecord.class);
    }
}
