package mipt.guchievmb.hw1.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import mipt.guchievmb.hw1.model.Book;
import mipt.guchievmb.hw1.service.BooksService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Collection;

@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
public class BooksController implements BooksControllerApi {

  private final BooksService booksService;

  @GetMapping
  public ResponseEntity<Collection<Book>> getAllBooks() {
    return ResponseEntity.ok(booksService.getAllBooks());
  }

  @GetMapping("/{id}")
  public ResponseEntity<Book> getBookById(@PathVariable Long id) {
    return ResponseEntity.ok(booksService.getBookById(id));
  }

  @PostMapping
  public ResponseEntity<Book> createBook(@RequestBody @Valid Book book, @RequestParam Long authorId) {
    return ResponseEntity.status(HttpStatus.CREATED).body(booksService.createBook(book, authorId));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
    booksService.deleteBook(id);
    return ResponseEntity.noContent().build();
  }
}