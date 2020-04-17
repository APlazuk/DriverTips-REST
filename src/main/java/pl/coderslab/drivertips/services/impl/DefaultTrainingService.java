package pl.coderslab.drivertips.services.impl;

import org.springframework.stereotype.Service;
import pl.coderslab.drivertips.domain.Training;
import pl.coderslab.drivertips.exceptions.TipNotFoundException;
import pl.coderslab.drivertips.exceptions.TrainingNotFoundException;
import pl.coderslab.drivertips.repositories.TrainingRepository;
import pl.coderslab.drivertips.services.TrainingService;

import java.util.Optional;

@Service
public class DefaultTrainingService implements TrainingService {

    private final TrainingRepository trainingRepository;

    public DefaultTrainingService(TrainingRepository trainingRepository) {
        this.trainingRepository = trainingRepository;
    }


    @Override
    public Training getTrainingByTipId(Long tipId) {

        Optional<Training> trainingByTipId = trainingRepository.findTrainingByTipId(tipId);

        if (trainingByTipId.isEmpty()) {
            throw new TrainingNotFoundException(String.format("Traning dla danego id porady: %s nie istnieje", tipId));
        }
        return trainingByTipId.get();
    }
}
