package mipt.guchievmb.hw1.service;

import lombok.RequiredArgsConstructor;
import mipt.guchievmb.hw1.model.University;
import mipt.guchievmb.hw1.repository.UniversitiesRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Collection;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class UniversitiesService {

  private final UniversitiesRepository universitiesRepository;

  @Transactional(readOnly = true)
  public Collection<University> getAllUniversities() {
    return universitiesRepository.findAll();
  }

  @Transactional(readOnly = true)
  public University getUniversityById(Long id) {
    return universitiesRepository.findById(id)
            .orElseThrow(() -> new NoSuchElementException("University with id " + id + " not found"));
  }

  @Transactional
  public University createUniversity(University university) {
    return universitiesRepository.save(university);
  }

  @Transactional
  public void deleteUniversity(Long id) {
    if (!universitiesRepository.existsById(id)) {
      throw new NoSuchElementException("University with id " + id + " not found");
    }
    universitiesRepository.deleteById(id);
  }
}