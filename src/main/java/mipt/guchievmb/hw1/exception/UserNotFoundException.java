package mipt.guchievmb.hw1.exception;

public class UserNotFoundException extends RuntimeException {
  public UserNotFoundException(String id) {
    super("User with id " + id + " not found");
  }
}
