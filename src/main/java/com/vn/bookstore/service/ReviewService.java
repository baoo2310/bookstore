package com.vn.bookstore.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.vn.bookstore.model.Review;
import com.vn.bookstore.model.Book;
import com.vn.bookstore.repository.ReviewRepository;
import com.vn.bookstore.repository.BookRepository;

@Service
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final BookRepository bookRepository;

    public ReviewService(ReviewRepository reviewRepository, BookRepository bookRepository){
        this.reviewRepository = reviewRepository;
        this.bookRepository = bookRepository;
    }

    public Review createReview(Review review){
        // Validate that the book exists
        if (review.getBook() != null && review.getBook().getBook_id() != null) {
            Book book = bookRepository.findById(review.getBook().getBook_id())
                .orElseThrow(() -> new RuntimeException("Book not found"));
            review.setBook(book);
        }
        return reviewRepository.save(review);
    }

    public Review getReviewById(Long id){
        return reviewRepository.findById(id).orElseThrow(() -> new RuntimeException("Review not found"));
    }

    public List<Review> getAllReviews(){
        return reviewRepository.findAll();
    }

    public List<Review> getReviewsByBookId(Long bookId){
        return reviewRepository.findByBookId(bookId);
    }

    public List<Review> getReviewsByBook(Book book){
        return reviewRepository.findByBook(book);
    }

    public Review updateReview(Long id, Review updatedReview) {
        Review existingReview = reviewRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Review not found"));
        
        if (updatedReview.getName() != null) {
            existingReview.setName(updatedReview.getName());
        }
        if (updatedReview.getDescription() != null) {
            existingReview.setDescription(updatedReview.getDescription());
        }
        if (updatedReview.getBook() != null && updatedReview.getBook().getBook_id() != null) {
            Book book = bookRepository.findById(updatedReview.getBook().getBook_id())
                .orElseThrow(() -> new RuntimeException("Book not found"));
            existingReview.setBook(book);
        }
        
        return reviewRepository.save(existingReview);
    }

    public boolean deleteReviewById(Long id) {
        if (reviewRepository.existsById(id)) {
            reviewRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
