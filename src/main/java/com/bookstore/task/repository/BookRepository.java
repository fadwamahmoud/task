package com.bookstore.task.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bookstore.task.domain.Book;

public interface BookRepository extends JpaRepository<Book, Long> {
}
