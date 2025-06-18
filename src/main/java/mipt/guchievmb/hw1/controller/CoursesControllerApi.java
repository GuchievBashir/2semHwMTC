package mipt.guchievmb.hw1.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import mipt.guchievmb.hw1.model.Course;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.Collection;

@Tag(name = "Courses API", description = "Операции для работы с курсами")
public interface CoursesControllerApi {

  @Operation(summary = "Получение всех курсов")
  ResponseEntity<Collection<Course>> getAllCourses();

  @Operation(summary = "Получение курса по ID")
  ResponseEntity<Course> getCourseById(@PathVariable Long id);

  @Operation(summary = "Создание курса")
  ResponseEntity<Course> createCourse(@RequestBody @Valid Course course);

  @Operation(summary = "Удаление курса")
  ResponseEntity<Void> deleteCourse(@PathVariable Long id);
}