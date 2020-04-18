package pl.coderslab.drivertips.services;

import pl.coderslab.drivertips.model.Tip;
import pl.coderslab.drivertips.model.Training;

public interface TrainingService {

    Training getTrainingByTipId(Long tipId);

    Training getTrainingById(Long id);

    Training createNewTraining(Training training, Tip tip);
}
