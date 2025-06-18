package mipt.guchievmb.hw1.repository;

import mipt.guchievmb.hw1.model.Book;
import mipt.guchievmb.hw1.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Testcontainers
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class BooksRepositoryTest {

  @Container
  private static final PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:15-alpine");

  @DynamicPropertySource
  static void configureProperties(DynamicPropertyRegistry registry) {
    registry.add("spring.datasource.url", postgres::getJdbcUrl);
    registry.add("spring.datasource.username", postgres::getUsername);
    registry.add("spring.datasource.password", postgres::getPassword);
  }

  @Autowired
  private BooksRepository booksRepository;

  @Autowired
  private UsersRepository usersRepository;

  @Test
  void shouldSaveAndFindBook() {
    User author = new User();
    author.setName("Test Author");
    usersRepository.save(author);

    Book book = new Book();
    book.setTitle("Test Book");
    book.setAuthor(author);

    Book savedBook = booksRepository.save(book);

    assertThat(savedBook.getId()).isNotNull();
  }
}