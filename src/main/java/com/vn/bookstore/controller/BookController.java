package com.vn.bookstore.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vn.bookstore.model.Book;
import com.vn.bookstore.service.BookService;


@RestController
@RequestMapping("/api/book")
public class BookController {
    private BookService bookService;
    public BookController(BookService BookService){
        this.bookService = BookService;
    }
    @GetMapping
    public List<Book> getAllBook() {
        return bookService.getAllBook();
    }

    @PostMapping
    public ResponseEntity<String> createBook(
        @RequestBody Book book
    ){
        bookService.createBook(book);
        return new ResponseEntity<>("Book added successfully", HttpStatus.CREATED);
    }
    

    @GetMapping("/{book-id}")
    public ResponseEntity<Book> getBookById(
        @PathVariable("book-id") Long id
    ) {
        Book book = bookService.getBookById(id);
        if(book != null) return new ResponseEntity<>(book, HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{book-id}")
    public ResponseEntity<String> deleteBook(
        @PathVariable("book-id") Long id
    ){
        boolean deleted = bookService.deleteBookById(id);
        if(deleted){
            return new ResponseEntity<>("Book deleted successfully", HttpStatus.OK); 
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
