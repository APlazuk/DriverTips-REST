package pl.coderslab.drivertips.services.impl;

import org.springframework.stereotype.Service;
import pl.coderslab.drivertips.exceptions.TrainingAlreadyExistsException;
import pl.coderslab.drivertips.model.Question;
import pl.coderslab.drivertips.model.Tip;
import pl.coderslab.drivertips.model.Training;
import pl.coderslab.drivertips.exceptions.TrainingNotFoundException;
import pl.coderslab.drivertips.repositories.QuestionRepository;
import pl.coderslab.drivertips.repositories.TrainingRepository;
import pl.coderslab.drivertips.services.TrainingService;

import java.util.List;
import java.util.Optional;

@Service
public class DefaultTrainingService implements TrainingService {

    private final TrainingRepository trainingRepository;
    private final QuestionRepository questionRepository;

    public DefaultTrainingService(TrainingRepository trainingRepository, QuestionRepository questionRepository) {
        this.trainingRepository = trainingRepository;
        this.questionRepository = questionRepository;
    }

    @Override
    public List<Training> getAll() {
        return trainingRepository.findAll();
    }

    @Override
    public Training getTrainingByTipId(Long tipId) {

        Optional<Training> trainingByTipId = trainingRepository.findTrainingByTipId(tipId);

        if (trainingByTipId.isEmpty()) {
            throw new TrainingNotFoundException(String.format("Traning dla danego id porady: %s nie istnieje", tipId));
        }
        return trainingByTipId.get();
    }

    @Override
    public Training findTrainingById(Long id) {
        Optional<Training> trainingFromDB = trainingRepository.findTrainingById(id);

        if (trainingFromDB.isEmpty()) {
            throw new TrainingNotFoundException(String.format("Traning dla id: %s nie istnieje", id));
        }
        return trainingFromDB.get();
    }

    @Override
    public Training createNewTraining(Training training, Tip tip) {

        if (trainingRepository.findTrainingByTipId(tip.getId()).isPresent()) {
            throw new TrainingAlreadyExistsException("Traning dla danej Porady już istnieje");
        }

        List<Question> questions = training.getQuestions();
        questions.forEach(questionRepository::save);
        questions.forEach(question -> question.setTraining(training));

        training.setTip(tip);

        return trainingRepository.save(training);
    }

    @Override
    public Training update(Long id, Training training) {
        Optional<Training> trainingFromDB = trainingRepository.findTrainingById(id);

        if (trainingFromDB.isEmpty()) {
            throw new TrainingNotFoundException(String.format("Traning dla id: %s nie istnieje", id));
        }

        return trainingFromDB.get();
    }

    @Override
    public void delete(Training training) {
        trainingRepository.deleteById(training);
    }
}
