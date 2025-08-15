package com.vn.bookstore.controller;

import org.springframework.web.bind.annotation.RestController;

import com.vn.bookstore.model.Book;
import com.vn.bookstore.model.User;
import com.vn.bookstore.service.UserService;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
public class UserController {
    private UserService userService;
    public UserController(UserService userService){
        this.userService = userService;
    }
    @GetMapping("/api/user")
    public List<User> getAllUser() {
        return userService.getAllUser();
    }

    @PostMapping({"/api/user", "/api/user/"})
    public ResponseEntity<String> createUser(
        @RequestBody User user
    ){
        userService.createUser(user);
        return new ResponseEntity<>("User added successfully", HttpStatus.CREATED);
    }
    

    @GetMapping("/api/user/{user-id}")
    public ResponseEntity<User> getUserById(
        @PathVariable Integer id
    ) {
        User user = userService.getUserById(id);
        if(user != null) return new ResponseEntity<>(user, HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/api/user/{user-id}")
    public ResponseEntity<String> deleteUser(
        @PathVariable Integer id
    ){
        boolean deleted = userService.deleteUserById(id);
        if(deleted){
            return new ResponseEntity<>("User deleted successfully", HttpStatus.OK); 
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/api/user/{user-id}/upload")
    public ResponseEntity<Book> uploadBook(
        @PathVariable Integer user_id,
        @RequestBody Book book
    ) {
        return new ResponseEntity<>(userService.uploadBook(user_id, book), HttpStatus.CREATED);
    }

    @PostMapping("/api/user/{user-id}/buy/{book-id}")
    public ResponseEntity<String> buyBook(
        @PathVariable Integer user_id,
        @PathVariable Long book_id
    ) {
        userService.buyBook(user_id, book_id);
        return new ResponseEntity<>("Book purchased successfully", HttpStatus.OK);
    }
    
    
}
