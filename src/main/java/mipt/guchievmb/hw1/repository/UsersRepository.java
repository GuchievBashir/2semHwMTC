package mipt.guchievmb.hw1.repository;

import mipt.guchievmb.hw1.model.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class UsersRepository {
  private final Map<String, User> users = new ConcurrentHashMap<>();
  private final AtomicLong idCounter = new AtomicLong(1);
  public List<User> getAllUsers() {
    return new ArrayList<>(users.values());
  }

  public Optional<User> getUserById(String id) {
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
