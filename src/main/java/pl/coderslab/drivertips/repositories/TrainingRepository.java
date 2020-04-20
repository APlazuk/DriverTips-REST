package pl.coderslab.drivertips.repositories;

import pl.coderslab.drivertips.model.Training;

import java.util.List;
import java.util.Optional;

public interface TrainingRepository {

    List<Training> findAll();

    Optional<Training> findTrainingByTipId(Long tipId);

    Optional<Training> findTrainingById(Long id);

    Training save(Training training);

    void deleteById(Long id);
}
