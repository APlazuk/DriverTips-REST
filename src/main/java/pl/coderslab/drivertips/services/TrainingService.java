package pl.coderslab.drivertips.services;

import pl.coderslab.drivertips.model.Training;

public interface TrainingService {

    Training getTrainingByTipId(Long tipId);
}
