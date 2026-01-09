package com.bookstore.task.dto.response;

public record BookResponse(Long id,
    String name,
    Integer rating,
    String author,
    Long version) {
    
}
