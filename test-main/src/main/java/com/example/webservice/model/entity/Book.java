package com.example.webservice.model.entity;

import com.example.webservice.model.enums.Genre;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "books")
@Data
@NoArgsConstructor
public class Book {
    @Id
    @GeneratedValue
    private Long id;
    private String title;
    private String isbn;
    private String author;
    private String description;
    @Enumerated(EnumType.STRING)
    private Genre genre;
}
