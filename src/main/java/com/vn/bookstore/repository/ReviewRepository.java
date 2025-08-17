package com.vn.bookstore.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.vn.bookstore.model.Review;
import com.vn.bookstore.model.Book;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByBook(Book book);
    
    @Query("SELECT r FROM Review r WHERE r.book.book_id = :bookId")
    List<Review> findByBookId(@Param("bookId") Long bookId);
    
    List<Review> findByName(String name);
}
