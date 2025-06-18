package mipt.guchievmb.hw1.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import mipt.guchievmb.hw1.model.University;
import mipt.guchievmb.hw1.service.UniversitiesService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Collection;

@RestController
@RequestMapping("/universities")
@RequiredArgsConstructor
public class UniversitiesController implements UniversityControllerApi {

  private final UniversitiesService universitiesService;

  @Override
  @GetMapping
  public ResponseEntity<Collection<University>> getUniversities() {
    return ResponseEntity.ok(universitiesService.getAllUniversities());
  }

  @Override
  @GetMapping("/{id}")
  public ResponseEntity<University> getUniversityById(@PathVariable Long id) {
    return ResponseEntity.ok(universitiesService.getUniversityById(id));
  }

  @Override
  @PostMapping
  public ResponseEntity<University> createUniversity(@RequestBody @Valid University university) {
    return ResponseEntity.status(HttpStatus.CREATED).body(universitiesService.createUniversity(university));
  }

  @Override
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteUniversity(@PathVariable Long id) {
    universitiesService.deleteUniversity(id);
    return ResponseEntity.noContent().build();
  }
}