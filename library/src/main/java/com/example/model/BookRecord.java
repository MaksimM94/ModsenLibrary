package com.example.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "books_records")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class BookRecord implements Serializable {
    @Id
    private Long book_id;
    private LocalDateTime borrowed_at;
    private LocalDateTime released_at;
}
