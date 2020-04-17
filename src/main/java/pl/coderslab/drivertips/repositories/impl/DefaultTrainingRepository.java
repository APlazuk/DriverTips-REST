package pl.coderslab.drivertips.repositories.impl;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.coderslab.drivertips.domain.Training;
import pl.coderslab.drivertips.repositories.TrainingRepository;

import java.util.Optional;

@Repository
interface DefaultTrainingRepository extends TrainingRepository, JpaRepository<Training, Long> {

    @Override
    @Query(value = "SELECT training.id, training.title FROM training JOIN tip t on training.tip_id = t.id WHERE t.id =?", nativeQuery = true)
    Optional<Training> findTrainingByTipId(Long tipId);
}
