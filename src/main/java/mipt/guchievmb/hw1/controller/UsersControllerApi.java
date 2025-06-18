package mipt.guchievmb.hw1.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import mipt.guchievmb.hw1.model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@Tag(name = "Users API", description = "Операции для работы с пользователями")
public interface UsersControllerApi {

  @Operation(summary = "Получение всех пользователей")
  ResponseEntity<Collection<User>> getAllUsers();

  @Operation(summary = "Получение пользователя по id")
  @ApiResponses({
          @ApiResponse(responseCode = "200", description = "Пользователь найден"),
          @ApiResponse(responseCode = "404", description = "Пользователь не найден")
  })
  ResponseEntity<User> getUserById(
          @Parameter(description = "Идентификатор пользователя", required = true)
          @PathVariable Long id);

  @Operation(summary = "Создание пользователя")
  @ApiResponses({
          @ApiResponse(responseCode = "201", description = "Пользователь успешно создан"),
          @ApiResponse(responseCode = "400", description = "Ошибка валидации входных данных")
  })
  ResponseEntity<User> createUser(
          @Parameter(description = "Объект пользователя для создания", required = true)
          @RequestBody @Valid User user);

  @Operation(summary = "Обновление пользователя")
  @ApiResponses({
          @ApiResponse(responseCode = "200", description = "Пользователь успешно обновлен"),
          @ApiResponse(responseCode = "404", description = "Пользователь не найден")
  })
  ResponseEntity<User> updateUser(
          @Parameter(description = "Идентификатор пользователя", required = true)
          @PathVariable Long id,
          @Parameter(description = "Обновленные данные пользователя", required = true)
          @RequestBody @Valid User user);

  @Operation(summary = "Удаление пользователя")
  @ApiResponses({
          @ApiResponse(responseCode = "204", description = "Пользователь успешно удален"),
          @ApiResponse(responseCode = "404", description = "Пользователь не найден")
  })
  ResponseEntity<Void> deleteUser(
          @Parameter(description = "Идентификатор пользователя", required = true)
          @PathVariable Long id);
}