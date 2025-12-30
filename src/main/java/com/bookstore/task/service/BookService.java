package com.bookstore.task.service;

import org.springframework.stereotype.Service;

import com.bookstore.task.repository.BookRepository;
import com.bookstore.task.domain.Book;
import java.util.List;

@Service
public class BookService {
    
    private final BookRepository repo;

    public BookService( BookRepository repo){
        this.repo = repo;
    }

    public List<Book> list(){
        return repo.findAll();

    }

    public Book create(Book book){
        book.setName(book.getName().trim());
        book.setAuthor(book.getAuthor().trim());
        return repo.save(book);
    }

    public Book update(Long id,Book updated){
      Book existing =  repo.findById(id)
      //later replace RuntimeException with a proper @ResponseStatus exception or a controller advice
      .orElseThrow(()-> new RuntimeException("Book not found"));

      existing.setName(updated.getName().trim());
      existing.setAuthor(updated.getAuthor().trim());
      existing.setRating(updated.getRating());

      return repo.save(existing);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }
}
