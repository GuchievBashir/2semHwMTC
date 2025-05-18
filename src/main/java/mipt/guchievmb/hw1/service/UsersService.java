package mipt.guchievmb.hw1.service;

import lombok.extern.slf4j.Slf4j;
import mipt.guchievmb.hw1.exception.UserNotFoundException;
import mipt.guchievmb.hw1.model.Book;
import mipt.guchievmb.hw1.model.User;
import mipt.guchievmb.hw1.repository.UsersRepository;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Slf4j
@Service
public class UsersService {

  private final UsersRepository usersRepository;

  public UsersService(UsersRepository usersRepository) {
    this.usersRepository = usersRepository;
  }

  @Async
  public CompletableFuture<Collection<User>> getAllUsers() {
    return CompletableFuture.completedFuture(usersRepository.getAllUsers());
  }

  public User getUserById(String id) {
    return usersRepository.getUserById(id).orElseThrow(() -> new UserNotFoundException(id));
  }

  public User createUser(User user) {
    //Если уже есть с таким name, вывести его
    for (User currentUser: usersRepository.getAllUsers()) {
      if (currentUser.getName().equals(user.getName())) {
        System.out.println("Книга с таким именем уже существует");
        return currentUser;
      }
    }
    return usersRepository.createUser(user);
  }

  @Retryable(retryFor = UserNotFoundException.class, maxAttempts = 5, backoff = @Backoff(delay = 10000))
  public User updateUser(User user) {
    User updatedUser = usersRepository.getUserById(user.getId()).orElseThrow(() -> new UserNotFoundException(user.getId()));
    updatedUser.setName(user.getName());
    updatedUser.setAge(user.getAge());
    return updatedUser;
  }

  public User patchUser(String id, User patchData) {
    User existingUser = usersRepository.getUserById(id)
            .orElseThrow(() -> new UserNotFoundException(id));

    if (patchData.getName() != null) {
      existingUser.setName(patchData.getName());
    }
    if (patchData.getAge() != 0) {
      existingUser.setAge(patchData.getAge());
    }

    return existingUser;
  }

  public void deleteUser(String id) {
    usersRepository.deleteUserById(id);
  }
}
