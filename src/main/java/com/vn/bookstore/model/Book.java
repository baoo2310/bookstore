package com.vn.bookstore.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "books")
public class Book {
    @Id 
    @GeneratedValue
    private Long book_id;

    private String bookName;
    private String author;
    private Integer yearPublished;
    private String imgURL;
    @ManyToOne
    @JoinColumn(name = "uploader_id")
    private User uploader;

    public Book() {}

    public Long getBook_id() {
        return book_id;
    }

    public void setBook_id(Long book_id) {
        this.book_id = book_id;
    }

    public User getUploader() {
        return uploader;
    }

    public void setUploader(User uploader) {
        this.uploader = uploader;
    }

    public Book(String bookName, String author, int yearPublished, String imgURL) {
        this.bookName = bookName;
        this.author = author;
        this.yearPublished = yearPublished;
        this.imgURL = imgURL;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYearPublished() {
        return yearPublished;
    }

    public void setYearPublished(Integer yearPublished) {
        this.yearPublished = yearPublished;
    }

    public String getImgURL() {
        return imgURL;
    }

    public void setImgURL(String imgURL) {
        this.imgURL = imgURL;
    }

    public String toString(){
        return  "Book:{" + 
                "\nBook Name: " + bookName +
                "\nauthor: " + author +
                "\n}";
    }
}
