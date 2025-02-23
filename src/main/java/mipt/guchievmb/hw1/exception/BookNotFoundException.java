package mipt.guchievmb.hw1.exception;

public class BookNotFoundException extends RuntimeException {
  public BookNotFoundException(String id) {
    super("Книга с id " + id + " не найдена.");
  }
}
