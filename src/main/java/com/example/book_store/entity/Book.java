package com.example.book_store.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "BOOKS")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Book {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    Long id;

    @Column(name = "NAME", nullable = false, length = 50)
    String name;

    @Column(name = "PRICE", nullable = false)
    int price;

    @Column(name = "DESCRIPTION", nullable = false, length = 500)
    String description;

    @ManyToOne
    @JoinColumn(name = "author_id")
    Author author;

}