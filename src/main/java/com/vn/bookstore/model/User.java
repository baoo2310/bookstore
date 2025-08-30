package com.vn.bookstore.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class User {
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer user_id;
    @Column(unique = true, nullable = false, length = 50)
    private String username;
    @Column(unique = false, nullable = false, length = 25)
    private String firstName;
    @Column(unique = false, nullable = false, length = 25)
    private String lastName;
    @Column(unique = true, nullable = false, length = 100)
    private String email;
    @Column
    private int age;

    @OneToMany(mappedBy = "uploader", cascade = CascadeType.ALL)
    private List<Book> uploadedBooks = new ArrayList<>();

    @ManyToMany
    @JoinTable(
        name = "user_purchased_books",
        joinColumns = @JoinColumn(name  = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "book_id")
    )
    private Set<Book> purchasedBooks = new HashSet<>();

    @OneToOne(
        mappedBy = "user",
        cascade = CascadeType.ALL
    )
    private UserProfile userProfile;

    @Column(nullable = false)
    private String password;
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "role")
    private Set<String> roles = new HashSet<>();


    public User() {}

    public User(String firstName, String lastName, String email, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.age = age;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Integer getUserId() {
        return user_id;
    }

    public void setUserId(Integer user_id) {
        this.user_id = user_id;
    }

    public UserProfile getUserProfile() {
        return userProfile;
    }

    public void setUserProfile(UserProfile userProfile) {
        this.userProfile = userProfile;
    }

    public Set<Book> getPurchasedBooks() {
        return purchasedBooks;
    }

    public void setPurchasedBooks(Set<Book> purchasedBooks) {
        this.purchasedBooks = purchasedBooks;
    }

    public List<Book> getUploadedBooks() {
        return uploadedBooks;
    }

    public void setUploadedBooks(List<Book> uploadedBooks) {
        this.uploadedBooks = uploadedBooks;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }

    public String toString(){
        return  "User:{" + 
                "\nFirst Name: " + firstName +
                "\nLastname: " + lastName +
                "\nEmail: " + email + 
                "\nAge: " + age + "\n}";
    }
}
