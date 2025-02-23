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
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UsersController.class)
public class UsersControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockitoBean
  private UsersService usersService;

  @Autowired
  private ObjectMapper objectMapper;

  @Test
  @DisplayName("Should return user by ID when user exists")
  public void shouldReturnUserByIdWhenUserExists() throws Exception {
    User user = new User();
    user.setId("1");
    user.setName("John Doe");
    user.setAge(30);

    when(usersService.getUserById("1")).thenReturn(user);

    mockMvc.perform(get("/users/1"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value("1"))
            .andExpect(jsonPath("$.name").value("John Doe"))
            .andExpect(jsonPath("$.age").value(30));
  }

  @Test
  @DisplayName("Should return different user by ID when user exists")
  public void shouldReturnDifferentUserByIdWhenUserExists() throws Exception {
    User user = new User();
    user.setId("2");
    user.setName("Jane Doe");
    user.setAge(25);

    when(usersService.getUserById("2")).thenReturn(user);

    mockMvc.perform(get("/users/2"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value("2"))
            .andExpect(jsonPath("$.name").value("Jane Doe"))
            .andExpect(jsonPath("$.age").value(25));
  }

  @Test
  @DisplayName("Should return Not Found status when user does not exist")
  public void shouldReturnNotFoundWhenUserDoesNotExist() throws Exception {
    when(usersService.getUserById("3")).thenThrow(new UserNotFoundException("3"));

    mockMvc.perform(get("/users/3"))
            .andExpect(status().isNotFound());
  }
}
