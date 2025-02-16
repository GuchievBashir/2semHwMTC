package mipt.guchievmb.hw1.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mipt.guchievmb.hw1.model.Book;
import mipt.guchievmb.hw1.model.User;
import mipt.guchievmb.hw1.service.BooksService;
import mipt.guchievmb.hw1.service.UsersService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/books")
@Slf4j
@RequiredArgsConstructor
public class BooksController {
  private final BooksService booksService;

  @GetMapping
  public ResponseEntity<Collection<Book>> getAllBooks() {
    Collection<Book> books = booksService.getAllBooks();
    log.info(".getAllBooks {}", books);
    return ResponseEntity.ok(books);
  }

  @RequestMapping
  public ResponseEntity<Book> createUser(@RequestBody @Valid Book book) {
    Book createdBook = booksService.createBook(book);
    log.info(".createBook {}", createdBook);
    return ResponseEntity.status(HttpStatus.CREATED).body(createdBook);
  }
}

