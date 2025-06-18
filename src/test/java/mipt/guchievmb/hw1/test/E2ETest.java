package mipt.guchievmb.hw1.test;

import mipt.guchievmb.hw1.model.User;
import mipt.guchievmb.hw1.repository.UsersRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
public class E2ETest {

  @Container
  private static final PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:15-alpine");

  @DynamicPropertySource
  static void configureProperties(DynamicPropertyRegistry registry) {
    registry.add("spring.datasource.url", postgres::getJdbcUrl);
    registry.add("spring.datasource.username", postgres::getUsername);
    registry.add("spring.datasource.password", postgres::getPassword);
    registry.add("spring.jpa.hibernate.ddl-auto", () -> "create-drop");
    registry.add("spring.flyway.enabled", () -> "false");
  }

  @Autowired
  private TestRestTemplate restTemplate;

  @Autowired
  private UsersRepository usersRepository;

  @BeforeEach
  void setUp() {
    usersRepository.deleteAll();
  }

  @Test
  @DisplayName("E2E: POST and GET User - should create and retrieve a user")
  public void testCreateAndGetUser() {
    User newUser = new User();
    newUser.setName("Test User");
    newUser.setAge(30);

    ResponseEntity<User> postResponse = restTemplate.postForEntity("/users", newUser, User.class);
    assertEquals(HttpStatus.CREATED, postResponse.getStatusCode());
    User createdUser = postResponse.getBody();
    assertNotNull(createdUser);
    assertNotNull(createdUser.getId());

    ResponseEntity<User> getResponse = restTemplate.getForEntity("/users/" + createdUser.getId(), User.class);
    assertEquals(HttpStatus.OK, getResponse.getStatusCode());
    User fetchedUser = getResponse.getBody();
    assertNotNull(fetchedUser);
    assertEquals(createdUser.getId(), fetchedUser.getId());
    assertEquals(newUser.getName(), fetchedUser.getName());
  }
}