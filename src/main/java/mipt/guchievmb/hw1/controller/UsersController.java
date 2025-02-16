package mipt.guchievmb.hw1.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mipt.guchievmb.hw1.model.User;
import mipt.guchievmb.hw1.service.UsersService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/users")
@Slf4j
@RequiredArgsConstructor
public class UsersController {
  private final UsersService usersService;

  @GetMapping
  public ResponseEntity<Collection<User>> getAllUsers() {
    Collection<User> users = usersService.getAllUsers();
    log.info(".getAllUsers {}", users);
    return ResponseEntity.ok(users);
  }

  @RequestMapping
  public ResponseEntity<User> createUser(@RequestBody @Valid User user) {
    User userCreated = usersService.createUser(user);
    log.info(".createUser {}", user);
    return ResponseEntity.status(HttpStatus.CREATED).body(userCreated);
  }
}
