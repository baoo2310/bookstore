package com.vn.bookstore.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.vn.bookstore.model.User;
import com.vn.bookstore.repository.UserRepository;

@Service
public class UserService {
    private final UserRepository repository;
    public UserService(UserRepository repository){
        this.repository = repository;
    }

    public User createUser(User user){
        if(!repository.findByEmail(user.getEmail()).isEmpty()){
            throw new IllegalArgumentException("Email already exists");
        }
        return repository.save(user);
    }

    public User getUserById(Integer id){
        return repository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
    }

    public List<User> getAllUser(){
        return repository.findAll();
    }

    public boolean deleteUserById(Integer id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }
}
