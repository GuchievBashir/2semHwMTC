package mipt.guchievmb.hw1.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import mipt.guchievmb.hw1.model.Book;
import mipt.guchievmb.hw1.model.Course;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Collection;
import java.util.Collections;

@Tag(name = "Courses API", description = "Операции для работы с курсами")
public interface CoursesControllerApi {
  @Operation(summary = "Получение всех курсов", description = "Возвращает список всех курсов")
  @ApiResponses(value = {
          @ApiResponse(responseCode = "200", description = "Список успешно получен")
  })
  ResponseEntity<Collection<Course>> getAllCourses();

  @Operation(summary = "Создание курса", description = "Создает новый курс")
  @ApiResponses({
          @ApiResponse(responseCode = "201", description = "Курс успешно создан"),
          @ApiResponse(responseCode = "400", description = "Ошибка валидации входных данных")
  })
  ResponseEntity<Course> createCourse(
          @Parameter(description = "Объект курса для создания", required = true)
          @RequestBody @Valid Course course);
}
