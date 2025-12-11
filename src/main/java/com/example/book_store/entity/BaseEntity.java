package com.example.book_store.entity;

import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreRemove;
import jakarta.persistence.PreUpdate;
import lombok.Data;

import java.time.LocalDateTime;

@MappedSuperclass
@Data
public class BaseEntity {


    private LocalDateTime created_at;
    private LocalDateTime updated_at;
    private LocalDateTime deleted_at;

    @PrePersist
    protected void onCreate(){
        created_at = LocalDateTime.now();
    }
    @PreUpdate
    protected void onUpdate(){
        updated_at = LocalDateTime.now();
    }
    @PreRemove
    protected void onDelete(){
        deleted_at = LocalDateTime.now();
    }
}
