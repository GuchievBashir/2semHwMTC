package mipt.guchievmb.hw1.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mipt.guchievmb.hw1.model.Course;
import mipt.guchievmb.hw1.model.User;
import mipt.guchievmb.hw1.service.CoursesService;
import mipt.guchievmb.hw1.service.UsersService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/users")
@Slf4j
@RequiredArgsConstructor
public class CoursesController {
  private final CoursesService coursesService;

  @GetMapping
  public ResponseEntity<Collection<Course>> getAllCourses() {
    Collection<Course> courses = coursesService.getAllCourses();
    log.info(".getAllCourses {}", courses);
    return ResponseEntity.ok(courses);
  }

  @RequestMapping
  public ResponseEntity<Course> createCourse(@Valid @RequestBody Course course) {
    Course createdCourse = coursesService.createCourse(course);
    log.info(".createCourse {}", createdCourse);
    return ResponseEntity.status(HttpStatus.CREATED).body(createdCourse);
  }
}

