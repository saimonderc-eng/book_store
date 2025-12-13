package com.example.book_store.repository.customRepository;

import com.example.book_store.dto.BookResponseDto;
import com.example.book_store.entity.Book;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class BookRepositoryImpl implements CustomBookRepository {

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public List<Book> dynamicSearch(BookResponseDto book) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Book> query = cb.createQuery(Book.class);
        Root<Book> root = query.from(Book.class);

        List<Predicate> predicates = new ArrayList<>();
        String name = book.getName();
        if(book != null){
            name = "%"  + name.toLowerCase() + name.trim() + "%";
            predicates.add(cb.like(cb.lower(root.get("name")), name));
        }
        String author = book.getAuthor();
        if(author != null){
            author = "%" + author.toLowerCase() + author.trim() + "%";
            predicates.add(cb.like(cb.lower(root.get("author")), author));
        }
        Double price = book.getPrice();
        if(price != null){
            predicates.add(cb.greaterThanOrEqualTo(root.get("price"), price));
        }
        String description = book.getDescription();
        if(description != null){
            description = "%" + description.toLowerCase() + "%";
            predicates.add(cb.like(cb.lower(root.get("description")), description));
        }
        Predicate[] array = predicates.toArray(new Predicate[0]);
        query.where(array);

        return entityManager.createQuery(query).getResultList();
    }
}
