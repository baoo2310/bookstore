package com.vn.bookstore.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vn.bookstore.model.Book;

public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByBookName(String bookName);
    List<Book> findByAuthor(String author);
}
