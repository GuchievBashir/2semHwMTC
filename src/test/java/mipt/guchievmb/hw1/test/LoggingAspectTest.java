package mipt.guchievmb.hw1.test;

import mipt.guchievmb.hw1.controller.UsersController;
import mipt.guchievmb.hw1.service.UsersService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.Collections;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;


@SpringBootTest
@Testcontainers
public class LoggingAspectTest {

  @Container
  private static final PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:15-alpine");

  @DynamicPropertySource
  static void configureProperties(DynamicPropertyRegistry registry) {
    registry.add("spring.datasource.url", postgres::getJdbcUrl);
    registry.add("spring.datasource.username", postgres::getUsername);
    registry.add("spring.datasource.password", postgres::getPassword);
  }

  @Autowired
  private UsersController userController;

  @MockBean
  private UsersService usersService;

  @Test
  public void testAspectIsCalled() {
    doReturn(Collections.emptyList()).when(usersService).getAllUsers();

    userController.getAllUsers();

    verify(usersService).getAllUsers();
  }
}