package com.vn.bookstore.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.vn.bookstore.model.Book;
import com.vn.bookstore.model.User;
import com.vn.bookstore.repository.BookRepository;
import com.vn.bookstore.repository.UserRepository;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final BookRepository bookRepository;
    public UserService(UserRepository userRepository, BookRepository bookRepository){
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
    }

    public User createUser(User user){
        if(!userRepository.findByEmail(user.getEmail()).isEmpty()){
            throw new IllegalArgumentException("Email already exists");
        }
        return userRepository.save(user);
    }

    public User getUserById(Integer id){
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
    }

    public List<User> getAllUser(){
        return userRepository.findAll();
    }

    public boolean deleteUserById(Integer id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public Book uploadBook(Integer user_id, Book book){
        User user = userRepository.findById(user_id).orElseThrow(() -> new RuntimeException("User not found"));
        book.setUploader(user);
        return bookRepository.save(book);
    }

    public void buyBook(Integer user_id, Long book_id){
        User user = userRepository.findById(user_id).orElseThrow(() -> new RuntimeException("User not found"));
        Book book = bookRepository.findById(book_id).orElseThrow(() -> new RuntimeException("Book not found"));
        if(user.getPurchasedBooks().contains(book)){
            throw new RuntimeException("Book already purchased");
        }
        user.getPurchasedBooks().add(book);
        userRepository.save(user);
    }
}
