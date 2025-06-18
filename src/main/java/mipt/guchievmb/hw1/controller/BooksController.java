package mipt.guchievmb.hw1.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mipt.guchievmb.hw1.model.Book;
import mipt.guchievmb.hw1.service.BooksService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/books")
@Slf4j
@RequiredArgsConstructor
public class BooksController implements BooksControllerApi {

  private final BooksService bookService;

  @Override
  @GetMapping
  public ResponseEntity<Collection<Book>> getAllBooks() {
    Collection<Book> books = bookService.getAllBooks();
    log.info("Получение всех книг: {}", books);
    return ResponseEntity.ok(books);
  }

  @Override
  @GetMapping("/{id}")
  public ResponseEntity<Book> getBookById(@PathVariable String id) {
    Book book = bookService.getBookById(id);
    log.info("Получение книги по id: {}", book);
    return ResponseEntity.ok(book);
  }

  @Override
  @PostMapping
  public ResponseEntity<Book> createBook(@RequestBody @Valid Book book) {
    Book createdBook = bookService.createBook(book);
    log.info("Создана книга: {}", createdBook);
    return ResponseEntity.status(HttpStatus.CREATED).body(createdBook);
  }

  @Override
  @PutMapping("/{id}")
  public ResponseEntity<Book> updateBook(@PathVariable String id, @RequestBody @Valid Book book) {
    book.setId(id);
    Book updatedBook = bookService.updateBook(book);
    log.info("Обновлена книга: {}", updatedBook);
    return ResponseEntity.ok(updatedBook);
  }

  @Override
  @PatchMapping("/{id}")
  public ResponseEntity<Book> patchBook(@PathVariable String id, @RequestBody Book patchData) {
    Book patchedBook = bookService.patchBook(id, patchData);
    log.info("Частично обновлена книга: {}", patchedBook);
    return ResponseEntity.ok(patchedBook);
  }

  @Override
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteBook(@PathVariable String id) {
    bookService.deleteBook(id);
    log.info("Удалена книга с id: {}", id);
    return ResponseEntity.noContent().build();
  }
}
