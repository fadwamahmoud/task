package com.bookstore.task.mapper;

import com.bookstore.task.domain.Book;
import com.bookstore.task.dto.response.BookResponse;
import com.bookstore.task.dto.rquest.CreateBookRequest;
import com.bookstore.task.dto.rquest.UpdateBookRequest;

public final class BookMapper {
    private BookMapper() {
    }

    public static Book ToEntity(CreateBookRequest req) {

        Book book = new Book(null, null, null);
        book.setAuthor(req.author());
        book.setName(req.name());
        book.setRating(req.rating());

        return book;
    }

    public static void apply(UpdateBookRequest req,Book book){
        book.setAuthor(req.author());
        book.setName(req.name());
        book.setRating(req.rating());

    }

    public static BookResponse toResponse(Book book){
        return new BookResponse(
            book.getId(),
            book.getName(),
            book.getRating(),
            book.getAuthor(),
            book.getVersion()
    );
    }

}
