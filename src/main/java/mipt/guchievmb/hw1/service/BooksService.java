package mipt.guchievmb.hw1.service;

import lombok.extern.slf4j.Slf4j;
import mipt.guchievmb.hw1.exception.BookNotFoundException;
import mipt.guchievmb.hw1.model.Book;
import mipt.guchievmb.hw1.repository.BooksRepository;
import mipt.guchievmb.hw1.repository.BooksRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Slf4j
@Service
public class BooksService {
  private final BooksRepository bookRepository;

  public BooksService(BooksRepository bookRepository) {
    this.bookRepository = bookRepository;
  }

  public Collection<Book> getAllBooks() {
    return bookRepository.getAllBooks();
  }

  public Book getBookById(String id) {
    return bookRepository.getBookById(id)
            .orElseThrow(() -> new BookNotFoundException(id));
  }

  public Book createBook(Book book) {
    return bookRepository.createBook(book);
  }

  public Book updateBook(Book book) {
    Book existingBook = bookRepository.getBookById(book.getId())
            .orElseThrow(() -> new BookNotFoundException(book.getId()));
    existingBook.setTitle(book.getTitle());
    existingBook.setAuthor(book.getAuthor());
    return existingBook;
  }

  public Book patchBook(String id, Book patchData) {
    Book existingBook = bookRepository.getBookById(id)
            .orElseThrow(() -> new BookNotFoundException(id));

    if (patchData.getTitle() != null && !patchData.getTitle().isEmpty()) {
      existingBook.setTitle(patchData.getTitle());
    }
    if (patchData.getAuthor() != null && !patchData.getAuthor().isEmpty()) {
      existingBook.setAuthor(patchData.getAuthor());
    }
    return existingBook;
  }

  public void deleteBook(String id) {
    bookRepository.deleteBookById(id);
  }
}
