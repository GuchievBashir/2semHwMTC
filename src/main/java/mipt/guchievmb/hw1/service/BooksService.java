package mipt.guchievmb.hw1.service;

import lombok.extern.slf4j.Slf4j;
import mipt.guchievmb.hw1.model.Book;
import mipt.guchievmb.hw1.repository.BooksRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;

@Slf4j
@Service
public class BooksService {

  private final BooksRepository booksRepository;

  public BooksService(BooksRepository booksRepository) {
    this.booksRepository = booksRepository;
  }

  public Collection<Book> getAllBooks() {
    return Collections.emptyList();
  }

  public Book createBook(Book book) {
    return book;
  }
}
