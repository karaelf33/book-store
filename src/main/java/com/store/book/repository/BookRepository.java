package com.store.book.repository;

import com.store.book.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {

    boolean existsByCode(String bookCode);
    Book findByCode(String code);

}
