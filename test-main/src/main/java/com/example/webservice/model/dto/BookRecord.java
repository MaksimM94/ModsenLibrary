package com.example.webservice.model.dto;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class BookRecord {
    private Long book_id;
    private LocalDateTime borrowed_at;
    private LocalDateTime released_at;
}