package com.bookstore.task.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bookstore.task.repository.BookRepository;

import com.bookstore.task.domain.Book;
import com.bookstore.task.exception.BookNotFoundException;

import java.util.List;

@Service
@Transactional
public class BookService {
    
    private final BookRepository repo;

    public BookService( BookRepository repo){
        this.repo = repo;
    }

    @Transactional(readOnly = true)
    public List<Book> list(){
        return repo.findAll();

    }

    public Integer count(){

        return repo.findAll().size();
    }

    public Book create(Book book){
        book.setName(book.getName().trim());
        book.setAuthor(book.getAuthor().trim());
        return repo.save(book);
    }

    public Book update(Long id,Book updated) throws BookNotFoundException{
      Book existing =  repo.findById(id)
      .orElseThrow(()-> new BookNotFoundException());

      existing.setName(updated.getName().trim());
      existing.setAuthor(updated.getAuthor().trim());
      existing.setRating(updated.getRating());

      return repo.save(existing);
    }

    public void delete(Long id) throws BookNotFoundException {
        if(!repo.existsById(id)){
            throw new BookNotFoundException();
        }
        repo.deleteById(id);
    }
}
