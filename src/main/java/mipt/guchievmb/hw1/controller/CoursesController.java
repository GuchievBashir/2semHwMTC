package mipt.guchievmb.hw1.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import mipt.guchievmb.hw1.model.Course;
import mipt.guchievmb.hw1.service.CoursesService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Collection;

@RestController
@RequestMapping("/courses")
@RequiredArgsConstructor
public class CoursesController implements CoursesControllerApi {

  private final CoursesService coursesService;

  @Override
  @GetMapping
  public ResponseEntity<Collection<Course>> getAllCourses() {
    return ResponseEntity.ok(coursesService.getAllCourses());
  }

  @Override
  @GetMapping("/{id}")
  public ResponseEntity<Course> getCourseById(@PathVariable Long id) {
    return ResponseEntity.ok(coursesService.getCourseById(id));
  }

  @Override
  @PostMapping
  public ResponseEntity<Course> createCourse(@Valid @RequestBody Course course) {
    return ResponseEntity.status(HttpStatus.CREATED).body(coursesService.createCourse(course));
  }

  @Override
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteCourse(@PathVariable Long id) {
    coursesService.deleteCourse(id);
    return ResponseEntity.noContent().build();
  }
}