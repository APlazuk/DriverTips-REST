package pl.coderslab.drivertips.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.coderslab.drivertips.domain.Training;

@Repository
public interface TrainingRepository extends JpaRepository<Training, Long> {
}