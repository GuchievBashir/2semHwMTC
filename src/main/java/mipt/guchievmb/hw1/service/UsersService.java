package mipt.guchievmb.hw1.service;

import lombok.RequiredArgsConstructor;
import mipt.guchievmb.hw1.exception.UserNotFoundException;
import mipt.guchievmb.hw1.model.User;
import mipt.guchievmb.hw1.repository.UsersRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Collection;

@Service
@RequiredArgsConstructor
public class UsersService {

  private final UsersRepository usersRepository;

  @Transactional(readOnly = true)
  public Collection<User> getAllUsers() {
    return usersRepository.findAll();
  }

  @Transactional(readOnly = true)
  public User getUserById(Long id) {
    return usersRepository.findById(id)
            .orElseThrow(() -> new UserNotFoundException(id));
  }

  @Transactional
  public User createUser(User user) {
    return usersRepository.save(user);
  }

  @Transactional
  public User updateUser(Long id, User userDetails) {
    User existingUser = getUserById(id);
    existingUser.setName(userDetails.getName());
    existingUser.setAge(userDetails.getAge());
    return usersRepository.save(existingUser);
  }

  @Transactional
  public void deleteUser(Long id) {
    if (!usersRepository.existsById(id)) {
      throw new UserNotFoundException(id);
    }
    usersRepository.deleteById(id);
  }
}