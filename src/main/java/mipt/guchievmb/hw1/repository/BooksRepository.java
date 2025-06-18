package mipt.guchievmb.hw1.repository;

import mipt.guchievmb.hw1.model.Book;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class BooksRepository {
  private final Map<String, Book> books = new ConcurrentHashMap<>();
  private final AtomicLong idCounter = new AtomicLong(1);

  public List<Book> getAllBooks() {
    return new ArrayList<>(books.values());
  }

  public Optional<Book> getBookById(String id) {
    return Optional.ofNullable(books.get(id));
  }

  public Book createBook(Book book) {
    book.setId(String.valueOf(idCounter.getAndIncrement()));
    books.put(book.getId(), book);
    return book;
  }

  public void deleteBookById(String id) {
    books.remove(id);
  }
}
