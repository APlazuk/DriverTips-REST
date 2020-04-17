package pl.coderslab.drivertips.repositories;

import pl.coderslab.drivertips.domain.Training;

import java.util.Optional;

public interface TrainingRepository {

    Optional<Training> findTrainingByTipId(Long tipId);
}
