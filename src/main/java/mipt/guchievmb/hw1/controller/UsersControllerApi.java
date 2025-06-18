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
import java.util.concurrent.CompletableFuture;

@Tag(name = "Users API", description = "Операции для работы с пользователями")
public interface UsersControllerApi {

  @Operation(summary = "Получение всех пользователей", description = "Возвращает список всех пользователей")
  @ApiResponses({
          @ApiResponse(responseCode = "200", description = "Список успешно получен")
  })
  CompletableFuture<ResponseEntity<Collection<User>>> getAllUsers();

  @Operation(summary = "Получение пользователя по id", description = "Возвращает пользователя по заданному идентификатору")
  @ApiResponses({
          @ApiResponse(responseCode = "200", description = "Пользователь найден"),
          @ApiResponse(responseCode = "404", description = "Пользователь не найден")
  })
  ResponseEntity<User> getUserById(
          @Parameter(description = "Идентификатор пользователя", required = true)
          @PathVariable String id);

  @Operation(summary = "Создание пользователя", description = "Создает нового пользователя")
  @ApiResponses({
          @ApiResponse(responseCode = "201", description = "Пользователь успешно создан"),
          @ApiResponse(responseCode = "400", description = "Ошибка валидации входных данных")
  })
  ResponseEntity<User> createUser(
          @Parameter(description = "Объект пользователя для создания", required = true)
          @RequestBody @Valid User user);

  @Operation(summary = "Обновление пользователя", description = "Полное обновление пользователя")
  @ApiResponses({
          @ApiResponse(responseCode = "200", description = "Пользователь успешно обновлен"),
          @ApiResponse(responseCode = "404", description = "Пользователь не найден")
  })
  ResponseEntity<User> updateUser(
          @Parameter(description = "Идентификатор пользователя", required = true)
          @PathVariable String id,
          @Parameter(description = "Обновленные данные пользователя", required = true)
          @RequestBody @Valid User user);

  @Operation(summary = "Частичное обновление пользователя", description = "Обновляет только переданные поля пользователя")
  @ApiResponses({
          @ApiResponse(responseCode = "200", description = "Пользователь успешно обновлен"),
          @ApiResponse(responseCode = "404", description = "Пользователь не найден")
  })
  ResponseEntity<User> patchUser(
          @Parameter(description = "Идентификатор пользователя", required = true)
          @PathVariable String id,
          @Parameter(description = "Данные для частичного обновления пользователя", required = true)
          @RequestBody User patchData);

  @Operation(summary = "Удаление пользователя", description = "Удаляет пользователя по заданному идентификатору")
  @ApiResponses({
          @ApiResponse(responseCode = "204", description = "Пользователь успешно удален"),
          @ApiResponse(responseCode = "404", description = "Пользователь не найден")
  })
  ResponseEntity<Void> deleteUser(
          @Parameter(description = "Идентификатор пользователя", required = true)
          @PathVariable String id);
}
