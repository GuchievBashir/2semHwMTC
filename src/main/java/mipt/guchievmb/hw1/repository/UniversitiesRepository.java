package mipt.guchievmb.hw1.repository;

import mipt.guchievmb.hw1.model.University;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UniversitiesRepository extends JpaRepository<University, Long> {
}