package mipt.guchievmb.hw1.service;

import lombok.extern.slf4j.Slf4j;
import mipt.guchievmb.hw1.model.Book;
import mipt.guchievmb.hw1.model.User;
import mipt.guchievmb.hw1.repository.UsersRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;

@Slf4j
@Service
public class UsersService {

  private final UsersRepository usersRepository;

  public UsersService(UsersRepository usersRepository) {
    this.usersRepository = usersRepository;
  }

  public Collection<User> getAllUsers() {
    return Collections.emptyList();
  }

  public User createUser(User user) {
    return user;
  }
}
