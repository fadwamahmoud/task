package com.bookstore.task.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false)
    private String name;

    @Min(1)
    @Max(5)
    @Column(nullable = false)
    private Integer rating;

    @Column(nullable = false)
    private String author;

    @Version
    private Long version;

    protected Book() {
    }
    
    public Book(String name, Integer rating, String author){
        this.name = name;
        this.rating = rating;
        this.author = author;
    }

   
    public Long getId() { return id; }
    public String getName() { return name; }
    public Integer getRating() { return rating; }
    public String getAuthor() { return author; }

    public void setName(String name) { this.name = name; }
    public void setRating(Integer rating) { this.rating = rating; }
    public void setAuthor(String author) { this.author = author; }
}
