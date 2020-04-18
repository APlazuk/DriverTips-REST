package pl.coderslab.drivertips.repositories;

import pl.coderslab.drivertips.model.Training;

import java.util.Optional;

public interface TrainingRepository {

    Optional<Training> findTrainingByTipId(Long tipId);

    Optional<Training> findTrainingById(Long id);

    Training save(Training training);
}
