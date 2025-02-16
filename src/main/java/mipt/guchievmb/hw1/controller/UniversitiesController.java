package mipt.guchievmb.hw1.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mipt.guchievmb.hw1.model.University;
import mipt.guchievmb.hw1.model.User;
import mipt.guchievmb.hw1.service.UniversitiesService;
import mipt.guchievmb.hw1.service.UsersService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/universities")
@Slf4j
@RequiredArgsConstructor
public class UniversitiesController {
  private final UniversitiesService universitiesService;

  @GetMapping
  public ResponseEntity<Collection<University>> getUniversities() {
    Collection<University> universities = universitiesService.getAllUniversities();
    log.info(".getAllUniversities {}", universities);
    return ResponseEntity.ok(universities);
  }

  @PostMapping
  public ResponseEntity<University> createUniversity(@Valid University university) {
    University createdUniversity = universitiesService.createUniversity(university);
    log.info(".createUniversity {}", createdUniversity);
    return ResponseEntity.status(HttpStatus.CREATED).body(createdUniversity);
  }
}
