package com.vn.bookstore.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.vn.bookstore.model.Book;
import com.vn.bookstore.repository.BookRepository;

@Service
public class BookService {
    private final BookRepository repository;

    public BookService(BookRepository repository){
        this.repository = repository;
    }

    public Book createBook(Book Book){
        if(!repository.findByBookName(Book.getBookName()).isEmpty()){
            throw new IllegalArgumentException("Book already exists");
        }
        return repository.save(Book);
    }

    public Book getBookById(Long id){
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Book not found"));
    }

    public List<Book> getAllBook(){
        return repository.findAll();
    }

    public boolean deleteBookById(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }
}
