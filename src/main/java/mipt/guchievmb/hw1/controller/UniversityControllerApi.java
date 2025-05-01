package mipt.guchievmb.hw1.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import mipt.guchievmb.hw1.model.University;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@Tag(name = "Universities API", description = "CRUD операции для работы с университетами")
public interface UniversityControllerApi {

  @Operation(
          summary = "Получение списка университетов",
          description = "Возвращает список всех университетов"
  )
  @ApiResponses({
          @ApiResponse(responseCode = "200", description = "Список университетов успешно получен")
  })
  ResponseEntity<Collection<University>> getUniversities();

  @Operation(
          summary = "Создание университета",
          description = "Создает новый университет с указанными данными"
  )
  @ApiResponses({
          @ApiResponse(responseCode = "201", description = "Университет успешно создан"),
          @ApiResponse(responseCode = "400", description = "Ошибка валидации данных")
  })
  ResponseEntity<University> createUniversity(
          @Parameter(description = "Объект университета для создания", required = true)
          @Valid University university);
}
