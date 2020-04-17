package pl.coderslab.drivertips.services;

import pl.coderslab.drivertips.domain.Training;

public interface TrainingService {

    Training getTrainingByTipId(Long tipId);
}
