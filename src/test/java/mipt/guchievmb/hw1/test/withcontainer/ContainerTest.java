package mipt.guchievmb.hw1.test.withcontainer;

import net.bytebuddy.utility.dispatcher.JavaDispatcher;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.junit.jupiter.api.Assertions.assertTrue;


@Testcontainers
public class ContainerTest {
  private static final Logger log = LoggerFactory.getLogger(ContainerTest.class);


  @Container
  static PostgreSQLContainer<?> pg = new PostgreSQLContainer<>("postgres:14")
          .withDatabaseName("testdb")
          .withUsername("test")
          .withPassword("whatever")
          .withInitScript("schema.sql");

  @BeforeAll
  static void setUp() {
    log.info("PostgreSQL контейнер хост: {}", pg.getHost() + ":" + pg.getFirstMappedPort());
    log.info("PostgreSQL URL соединения: {}", pg.getJdbcUrl());
  }

  @Test
  public void testDatabaseConnection() {
    assertTrue(pg.isRunning());
  }
}

