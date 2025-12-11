package com.example.book_store.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "AUTHORS")
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    Long id;

    @Column(name = "FULL_NAME", nullable = false)
    String authorFullName;

    @Column(name = "BIRTHDATE", nullable = false)
    LocalDate birthdate;
}
