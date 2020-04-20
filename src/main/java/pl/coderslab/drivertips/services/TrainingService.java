package pl.coderslab.drivertips.services;

import pl.coderslab.drivertips.model.Tip;
import pl.coderslab.drivertips.model.Training;

import java.util.List;

public interface TrainingService {

    List<Training> getAll();

    Training getTrainingByTipId(Long tipId);

    Training findTrainingById(Long id);

    Training createNewTraining(Training training, Tip tip);

    Training update(Long id, Training training);

    void delete(Training training);
}
