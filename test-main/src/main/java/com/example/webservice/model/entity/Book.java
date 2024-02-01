package com.example.webservice.model.entity;

import com.example.webservice.model.enums.Genre;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Persistable;

@Entity
@Table(name = "books")
@Data
@NoArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "books_seq")
    @SequenceGenerator(name = "books_seq", sequenceName = "books_id_gen_seq", initialValue = 3, allocationSize = 50000)
    private Long id;
    private String title;
    private String isbn;
    private String author;
    private String description;
    @Enumerated(EnumType.STRING)
    private Genre genre;
}
