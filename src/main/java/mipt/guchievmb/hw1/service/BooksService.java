package mipt.guchievmb.hw1.service;

import lombok.RequiredArgsConstructor;
import mipt.guchievmb.hw1.exception.BookNotFoundException;
import mipt.guchievmb.hw1.exception.UserNotFoundException;
import mipt.guchievmb.hw1.model.Book;
import mipt.guchievmb.hw1.model.User;
import mipt.guchievmb.hw1.repository.BooksRepository;
import mipt.guchievmb.hw1.repository.UsersRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Collection;

@Service
@RequiredArgsConstructor
public class BooksService {

  private final BooksRepository booksRepository;
  private final UsersRepository usersRepository;

  @Transactional(readOnly = true)
  public Collection<Book> getAllBooks() {
    return booksRepository.findAll();
  }

  @Transactional(readOnly = true)
  public Book getBookById(Long id) {
    return booksRepository.findById(id)
            .orElseThrow(() -> new BookNotFoundException(id));
  }

  @Transactional
  public Book createBook(Book book, Long authorId) {
    User author = usersRepository.findById(authorId)
            .orElseThrow(() -> new UserNotFoundException(authorId));
    book.setAuthor(author);
    return booksRepository.save(book);
  }

  @Transactional
  public void deleteBook(Long id) {
    if (!booksRepository.existsById(id)) {
      throw new BookNotFoundException(id);
    }
    booksRepository.deleteById(id);
  }
}