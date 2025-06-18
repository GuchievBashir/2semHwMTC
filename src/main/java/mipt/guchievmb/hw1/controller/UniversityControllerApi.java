package mipt.guchievmb.hw1.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import mipt.guchievmb.hw1.model.University;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.Collection;

@Tag(name = "Universities API", description = "CRUD операции для работы с университетами")
public interface UniversityControllerApi {

  @Operation(summary = "Получение списка университетов")
  ResponseEntity<Collection<University>> getUniversities();

  @Operation(summary = "Получение университета по ID")
  ResponseEntity<University> getUniversityById(@PathVariable Long id);

  @Operation(summary = "Создание университета")
  ResponseEntity<University> createUniversity(@RequestBody @Valid University university);

  @Operation(summary = "Удаление университета")
  ResponseEntity<Void> deleteUniversity(@PathVariable Long id);
}