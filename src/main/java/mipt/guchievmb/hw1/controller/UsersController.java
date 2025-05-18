package mipt.guchievmb.hw1.controller;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mipt.guchievmb.hw1.model.User;
import mipt.guchievmb.hw1.service.UsersService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/users")
@Slf4j
@RequiredArgsConstructor
@RateLimiter(name = "apiLimiter")
@CircuitBreaker(name = "apiCB", fallbackMethod = "fallback")
public class UsersController implements UsersControllerApi {

  private final UsersService usersService;

  @Override
  @GetMapping
  public CompletableFuture<ResponseEntity<Collection<User>>> getAllUsers() {
    return usersService.getAllUsers()
            .thenApply(users -> {
              log.info("Получение всех пользователей: {}", users);
              return ResponseEntity.ok(users);
            });
  }

  @Override
  @GetMapping("/{id}")
  public ResponseEntity<User> getUserById(@PathVariable String id) {
    User user = usersService.getUserById(id);
    log.info("Получение пользователя по id: {}", user);
    return ResponseEntity.ok(user);
  }

  @Override
  @PostMapping
  public ResponseEntity<User> createUser(@RequestBody @Valid User user) {
    User createdUser = usersService.createUser(user);
    log.info("Создан пользователь: {}", createdUser);
    return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
  }

  @Override
  @PutMapping("/{id}")
  public ResponseEntity<User> updateUser(@PathVariable String id, @RequestBody @Valid User user) {
    user.setId(id);
    User updatedUser = usersService.updateUser(user);
    log.info("Обновление пользователя: {}", updatedUser);
    return ResponseEntity.ok(updatedUser);
  }

  @Override
  @PatchMapping("/{id}")
  public ResponseEntity<User> patchUser(@PathVariable String id, @RequestBody User patchData) {
    User patchedUser = usersService.patchUser(id, patchData);
    log.info("Частичное обновление пользователя: {}", patchedUser);
    return ResponseEntity.ok(patchedUser);
  }

  @Override
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteUser(@PathVariable String id) {
    usersService.deleteUser(id);
    log.info("Удаление пользователя с id: {}", id);
    return ResponseEntity.noContent().build();
  }

  public String fallback(Throwable t) {
    return "Service temporarily unavailable";
  }
}
