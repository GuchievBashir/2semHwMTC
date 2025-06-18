package mipt.guchievmb.hw1.test;

import mipt.guchievmb.hw1.Application;
import mipt.guchievmb.hw1.model.User;
import mipt.guchievmb.hw1.security.SecurityConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@ContextConfiguration(classes={Application.class, SecurityConfig.class})
public class E2ETest {

  @Autowired
  private TestRestTemplate restTemplate;

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
    assertEquals(newUser.getAge(), fetchedUser.getAge());
  }

  @Test
  @DisplayName("E2E: PUT Update User - should update user data fully")
  public void testUpdateUserWithPut() {
    User user = new User();
    user.setName("Old Name");
    user.setAge(25);

    ResponseEntity<User> postResponse = restTemplate.postForEntity("/users", user, User.class);
    assertEquals(HttpStatus.CREATED, postResponse.getStatusCode());
    User createdUser = postResponse.getBody();
    assertNotNull(createdUser);
    String userId = createdUser.getId();

    User updatedData = new User();
    updatedData.setName("New Name");
    updatedData.setAge(35);

    HttpEntity<User> putRequest = new HttpEntity<>(updatedData);
    ResponseEntity<User> putResponse = restTemplate.exchange("/users/" + userId, HttpMethod.PUT, putRequest, User.class);
    assertEquals(HttpStatus.OK, putResponse.getStatusCode());
    User updatedUser = putResponse.getBody();
    assertNotNull(updatedUser);
    assertEquals("New Name", updatedUser.getName());
    assertEquals(35, updatedUser.getAge());
  }

  @Test
  @DisplayName("E2E: PATCH Update User - should partially update user data")
  public void testPartialUpdateUserWithPatch() {
    User user = new User();
    user.setName("Partial Old");
    user.setAge(40);

    ResponseEntity<User> postResponse = restTemplate.postForEntity("/users", user, User.class);
    assertEquals(HttpStatus.CREATED, postResponse.getStatusCode());
    User createdUser = postResponse.getBody();
    assertNotNull(createdUser);
    String userId = createdUser.getId();

    User patchData = new User();
    patchData.setName("Partial New");

    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    HttpEntity<User> patchRequest = new HttpEntity<>(patchData, headers);
    ResponseEntity<User> patchResponse = restTemplate.exchange("/users/" + userId, HttpMethod.PATCH, patchRequest, User.class);
    assertEquals(HttpStatus.OK, patchResponse.getStatusCode());
    User patchedUser = patchResponse.getBody();
    assertNotNull(patchedUser);
    assertEquals("Partial New", patchedUser.getName());
    assertEquals(40, patchedUser.getAge());
  }

  @Test
  @DisplayName("E2E: DELETE User - should delete user and return 404 on subsequent GET")
  public void testDeleteUser() {
    User user = new User();
    user.setName("To be deleted");
    user.setAge(28);

    ResponseEntity<User> postResponse = restTemplate.postForEntity("/users", user, User.class);
    assertEquals(HttpStatus.CREATED, postResponse.getStatusCode());
    User createdUser = postResponse.getBody();
    assertNotNull(createdUser);
    String userId = createdUser.getId();

    ResponseEntity<Void> deleteResponse = restTemplate.exchange("/users/" + userId, HttpMethod.DELETE, null, Void.class);
    assertEquals(HttpStatus.NO_CONTENT, deleteResponse.getStatusCode());

    ResponseEntity<User> getResponse = restTemplate.getForEntity("/users/" + userId, User.class);
    assertEquals(HttpStatus.NOT_FOUND, getResponse.getStatusCode());
  }

  @Test
  @DisplayName("E2E: GET All Users - should return a list containing created users")
  public void testGetAllUsers() {
    User user = new User();
    user.setName("List Test User");
    user.setAge(32);

    restTemplate.postForEntity("/users", user, User.class);

    ResponseEntity<User[]> response = restTemplate.getForEntity("/users", User[].class);
    assertEquals(HttpStatus.OK, response.getStatusCode());
    User[] users = response.getBody();
    assertNotNull(users);
    assertTrue(users.length > 0);
  }
}
