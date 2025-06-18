package mipt.guchievmb.hw1.service;

import lombok.RequiredArgsConstructor;
import mipt.guchievmb.hw1.model.Course;
import mipt.guchievmb.hw1.repository.CoursesRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Collection;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class CoursesService {

  private final CoursesRepository coursesRepository;

  @Transactional(readOnly = true)
  public Collection<Course> getAllCourses() {
    return coursesRepository.findAll();
  }

  @Transactional(readOnly = true)
  public Course getCourseById(Long id) {
    return coursesRepository.findById(id)
            .orElseThrow(() -> new NoSuchElementException("Course with id " + id + " not found"));
  }

  @Transactional
  public Course createCourse(Course course) {
    return coursesRepository.save(course);
  }

  @Transactional
  public void deleteCourse(Long id) {
    if (!coursesRepository.existsById(id)) {
      throw new NoSuchElementException("Course with id " + id + " not found");
    }
    coursesRepository.deleteById(id);
  }
}