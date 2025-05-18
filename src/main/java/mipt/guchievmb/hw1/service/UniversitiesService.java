package mipt.guchievmb.hw1.service;

import lombok.extern.slf4j.Slf4j;
import mipt.guchievmb.hw1.model.University;
import mipt.guchievmb.hw1.repository.UniversitiesRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;

@Slf4j
@Service
public class UniversitiesService {
  private final UniversitiesRepository universitiesRepository;

  public UniversitiesService(UniversitiesRepository universitiesRepository) {
    this.universitiesRepository = universitiesRepository;
  }

  public Collection<University> getAllUniversities() {
    return Collections.emptyList();
  }

  public University createUniversity(University university) {
    return university;
  }
}
