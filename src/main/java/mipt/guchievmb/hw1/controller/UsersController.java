package mipt.guchievmb.hw1.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import mipt.guchievmb.hw1.model.User;
import mipt.guchievmb.hw1.service.UsersService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Collection;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UsersController implements UsersControllerApi {

  private final UsersService usersService;

  @GetMapping
  public ResponseEntity<Collection<User>> getAllUsers() {
    return ResponseEntity.ok(usersService.getAllUsers());
  }

  @GetMapping("/{id}")
  public ResponseEntity<User> getUserById(@PathVariable Long id) {
    return ResponseEntity.ok(usersService.getUserById(id));
  }

  @PostMapping
  public ResponseEntity<User> createUser(@RequestBody @Valid User user) {
    return ResponseEntity.status(HttpStatus.CREATED).body(usersService.createUser(user));
  }

  @PutMapping("/{id}")
  public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody @Valid User user) {
    return ResponseEntity.ok(usersService.updateUser(id, user));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
    usersService.deleteUser(id);
    return ResponseEntity.noContent().build();
  }
}