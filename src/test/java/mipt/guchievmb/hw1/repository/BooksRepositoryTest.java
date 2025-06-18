package mipt.guchievmb.hw1.repository;

import mipt.guchievmb.hw1.model.Book;
import mipt.guchievmb.hw1.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Testcontainers
public class BooksRepositoryTest {

  @Container
  private static final PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:15-alpine");

  @DynamicPropertySource
  static void configureProperties(DynamicPropertyRegistry registry) {
    registry.add("spring.datasource.url", postgres::getJdbcUrl);
    registry.add("spring.datasource.username", postgres::getUsername);
    registry.add("spring.datasource.password", postgres::getPassword);
    registry.add("spring.jpa.hibernate.ddl-auto", () -> "create-drop");
  }

  @Autowired
  private BooksRepository booksRepository;

  @Autowired
  private UsersRepository usersRepository;

  @Test
  void shouldSaveAndFindBook() {
    User author = new User();
    author.setName("Test Author");
    author.setAge(42);
    User savedAuthor = usersRepository.save(author);

    Book book = new Book();
    book.setTitle("Test Book");
    book.setAuthor(savedAuthor);

    Book savedBook = booksRepository.save(book);

    assertThat(savedBook.getId()).isNotNull();
    Book foundBook = booksRepository.findById(savedBook.getId()).orElse(null);
    assertThat(foundBook).isNotNull();
    assertThat(foundBook.getTitle()).isEqualTo("Test Book");
    assertThat(foundBook.getAuthor().getName()).isEqualTo("Test Author");
  }
}