package mipt.guchievmb.hw1.repository;

import lombok.RequiredArgsConstructor;
import mipt.guchievmb.hw1.model.User;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
@RequiredArgsConstructor
public class UsersRepository {
  private final Map<String, User> users = new ConcurrentHashMap<>();
  private final AtomicLong idCounter = new AtomicLong(1);

  private final RestTemplate restTemplate;

  private final WebClient webClient;

  public List<User> getAllUsers() {
    String response = restTemplate.getForObject("https://httpbin.org/uuid", String.class);
    System.out.println("Ответ от RestTemplate: " + response);
    return new ArrayList<>(users.values());
  }

  public Optional<User> getUserById(String id) {
    String response = webClient.get()
            .uri("https://httpbin.org/ip")
            .retrieve()
            .bodyToMono(String.class)
            .block();
    return Optional.ofNullable(users.get(id));
  }

  public User createUser(User user) {
    user.setId(String.valueOf(idCounter.getAndIncrement()));
    users.put(user.getId(), user);
    return user;
  }

    public void deleteUserById(String id) {
    users.remove(id);
  }
}
