package mipt.guchievmb.hw1.test;

import com.fasterxml.jackson.databind.ObjectMapper;
import mipt.guchievmb.hw1.controller.UsersController;
import mipt.guchievmb.hw1.exception.UserNotFoundException;
import mipt.guchievmb.hw1.model.User;
import mipt.guchievmb.hw1.service.UsersService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UsersController.class)
public class UsersControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private UsersService usersService;


  @Test
  @DisplayName("Should return user by ID when user exists")
  public void shouldReturnUserByIdWhenUserExists() throws Exception {
    User user = new User();
    user.setId(1L);
    user.setName("John Doe");
    user.setAge(30);

    when(usersService.getUserById(1L)).thenReturn(user);

    mockMvc.perform(get("/users/1"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(1))
            .andExpect(jsonPath("$.name").value("John Doe"));
  }

  @Test
  @DisplayName("Should return Not Found status when user does not exist")
  public void shouldReturnNotFoundWhenUserDoesNotExist() throws Exception {
    when(usersService.getUserById(99L)).thenThrow(new UserNotFoundException(99L));

    mockMvc.perform(get("/users/99"))
            .andExpect(status().isNotFound());
  }
}