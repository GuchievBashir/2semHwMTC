package mipt.guchievmb.hw1.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class BookNotFoundException extends RuntimeException {
  public BookNotFoundException(Long id) {
    super("Книга с id " + id + " не найдена.");
  }
}