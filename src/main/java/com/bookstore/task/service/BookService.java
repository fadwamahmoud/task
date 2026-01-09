package com.bookstore.task.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bookstore.task.repository.BookRepository;

import com.bookstore.task.domain.Book;
import com.bookstore.task.dto.response.BookResponse;
import com.bookstore.task.dto.rquest.CreateBookRequest;
import com.bookstore.task.dto.rquest.UpdateBookRequest;
import com.bookstore.task.exception.BookNotFoundException;
import com.bookstore.task.mapper.BookMapper;

import java.util.List;

@Service
@Transactional
public class BookService {

  private final BookRepository repo;

  public BookService(BookRepository repo) {
    this.repo = repo;
  }

  @Transactional(readOnly = true)
  public List<BookResponse> list() {
    return repo.findAll().stream().map((book) -> BookMapper.toResponse(book)).toList();

  }

  public Integer count() {

    return repo.findAll().size();
  }

  public BookResponse create(CreateBookRequest req) {
    Book saved = repo.save(BookMapper.ToEntity(req));

    return BookMapper.toResponse(saved);
  }

  // partial update ie patch
  public BookResponse update(Long id, UpdateBookRequest req) throws BookNotFoundException {
    Book book = repo.findById(id)
        .orElseThrow(() -> new BookNotFoundException());

    // Optional optimistic check at API level (recommended for clearer 409):
    if (!book.getVersion().equals(req.version()))
      // throw your own exception that maps to 409,
      // OR throw an OptimisticLockingFailureException
      throw new org.springframework.dao.OptimisticLockingFailureException(
          "Stale version. Expected " + book.getVersion() + " but got " + req.version());

    BookMapper.apply(req, book);

    Book saved = repo.save(book);
    return BookMapper.toResponse(saved);
  }

  public void delete(Long id) throws BookNotFoundException {
    if (!repo.existsById(id)) {
      throw new BookNotFoundException();
    }
    repo.deleteById(id);
  }
}
