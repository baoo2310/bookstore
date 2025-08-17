package com.vn.bookstore.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vn.bookstore.model.Review;
import com.vn.bookstore.service.ReviewService;

@RestController
@RequestMapping("/api/review")
public class ReviewController {
    private ReviewService reviewService;
    
    public ReviewController(ReviewService reviewService){
        this.reviewService = reviewService;
    }
    
    @GetMapping
    public List<Review> getAllReviews() {
        return reviewService.getAllReviews();
    }

    @PostMapping
    public ResponseEntity<String> createReview(
        @RequestBody Review review
    ){
        try {
            reviewService.createReview(review);
            return new ResponseEntity<>("Review added successfully", HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    
    @GetMapping("/{review-id}")
    public ResponseEntity<Review> getReviewById(
        @PathVariable("review-id") Long id
    ) {
        try {
            Review review = reviewService.getReviewById(id);
            return new ResponseEntity<>(review, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/book/{book-id}")
    public ResponseEntity<List<Review>> getReviewsByBookId(
        @PathVariable("book-id") Long bookId
    ) {
        List<Review> reviews = reviewService.getReviewsByBookId(bookId);
        return new ResponseEntity<>(reviews, HttpStatus.OK);
    }

    @PutMapping("/{review-id}")
    public ResponseEntity<String> updateReview(
        @PathVariable("review-id") Long id,
        @RequestBody Review review
    ){
        try {
            reviewService.updateReview(id, review);
            return new ResponseEntity<>("Review updated successfully", HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{review-id}")
    public ResponseEntity<String> deleteReview(
        @PathVariable("review-id") Long id
    ){
        boolean deleted = reviewService.deleteReviewById(id);
        if(deleted){
            return new ResponseEntity<>("Review deleted successfully", HttpStatus.OK); 
        }
        return new ResponseEntity<>("Review not found", HttpStatus.NOT_FOUND);
    }
}
