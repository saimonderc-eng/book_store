package com.example.book_store.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.SQLDelete;


import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "BOOKS")
@FieldDefaults(level = AccessLevel.PRIVATE)
@SQLDelete(sql = "update books set deleted = true where id = ?")
public class Book {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    Long id;

    @Column(name = "NAME", nullable = false, length = 50)
    String name;

    @Column(name = "PRICE", nullable = false)
    Double price;

    @Column(name = "DESCRIPTION", nullable = false, length = 500)
    String description;

    @Column(name = "AUTHOR")
    String author;

    @Column(name = "STOCK_QUANTITY", nullable = false)
    Integer stockQuantity = 0;

    @Column(name = "DELETED", nullable = false)
    Boolean deleted = false;

    public Boolean isAvailable(){
        return stockQuantity != null && stockQuantity > 0;
    }
    public String getStockLevel(){
        if(stockQuantity == null){
            return "статус не известен";
        }
        if(stockQuantity <= 0){
            return "нет в наличие";
        }else if(stockQuantity <= 5){
            return "осталось мало!";
        }else {
            return "есть в наличие";
        }
    }

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "books_categories",
            joinColumns = @JoinColumn(name = "BOOK_ID"),
            inverseJoinColumns = @JoinColumn(name = "CATEGORY_ID")
    )
    List<Category> categories;
}