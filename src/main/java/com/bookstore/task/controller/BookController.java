package com.bookstore.task.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;


import com.bookstore.task.domain.Book;
import com.bookstore.task.service.BookService;

import jakarta.validation.Valid;

@RestController
public class BookController {

    private final BookService service;

    
    public BookController(BookService service){
        this.service = service;

    }

    @GetMapping("/books")
    public List<Book> list(){

        return service.list();
    }

    @PostMapping("/book")
    public Book create(@Valid @RequestBody Book book){

        return service.create(book);
    }


    @PutMapping("/book/{id}")
    public Book update(@PathVariable Long id, @Valid @RequestBody Book book){
        return service.update(id, book);
    }

    @DeleteMapping("/book/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

}
