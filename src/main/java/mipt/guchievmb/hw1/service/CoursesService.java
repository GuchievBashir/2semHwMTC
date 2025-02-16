package mipt.guchievmb.hw1.service;

import lombok.extern.slf4j.Slf4j;
import mipt.guchievmb.hw1.model.Course;
import mipt.guchievmb.hw1.repository.CoursesRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;

@Slf4j
@Service
public class CoursesService {
  private final CoursesRepository coursesRepository;

  public CoursesService(CoursesRepository coursesRepository) {
    this.coursesRepository = coursesRepository;
  }

  public Collection<Course> getAllCourses() {
    return Collections.emptyList();
  }

  public Course createCourse(Course course) {
    return course;
  }
}
