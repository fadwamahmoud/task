package com.bookstore.task.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.bookstore.task.dto.response.BookResponse;
import com.bookstore.task.dto.rquest.CreateBookRequest;
import com.bookstore.task.dto.rquest.UpdateBookRequest;
import com.bookstore.task.exception.BookNotFoundException;
import com.bookstore.task.service.BookService;

import jakarta.validation.Valid;

@RestController
public class BookController {

    private final BookService service;

    public BookController(BookService service) {
        this.service = service;

    }

    @GetMapping("/books")
    public List<BookResponse> list() {

        return service.list();
    }

    @GetMapping("/books/count")
    public Integer count() {

        return service.count();
    }

    @PostMapping("/book")
    @ResponseStatus(HttpStatus.CREATED)
    public BookResponse create(@Valid @RequestBody CreateBookRequest req) {

        return service.create(req);
    }

    @PutMapping("/book/{id}")
    public BookResponse update(@PathVariable Long id, @Valid @RequestBody UpdateBookRequest req) throws BookNotFoundException {

        return service.update(id, req);

    }

    @DeleteMapping("/book/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) throws BookNotFoundException {
        service.delete(id);
    }

}
