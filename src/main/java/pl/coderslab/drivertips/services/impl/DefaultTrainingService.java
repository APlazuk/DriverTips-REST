package pl.coderslab.drivertips.services.impl;

import org.springframework.stereotype.Service;
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
    public Training getTrainingByTipId(Long tipId) {

        Optional<Training> trainingByTipId = trainingRepository.findTrainingByTipId(tipId);

        if (trainingByTipId.isEmpty()) {
            throw new TrainingNotFoundException(String.format("Traning dla danego id porady: %s nie istnieje", tipId));
        }
        return trainingByTipId.get();
    }

    @Override
    public Training getTrainingById(Long id) {
        Optional<Training> trainingFromDB = trainingRepository.findTrainingById(id);

        if(trainingFromDB.isEmpty()){
            throw new TrainingNotFoundException(String.format("Traning dla id: %s nie istnieje",id));
        }
        return trainingFromDB.get();
    }

    //trzeba rzucić wyjątkiem jeśli chcemy dodać trening z ram użytym tipem. Postman wyrzuca 500 -More than one row with the given identifier was found: 1
    @Override
    public Training createNewTraining(Training training, Tip tip) {
//        List<Question>questions = questionRepository.getQuestionsByTrainingId(training.getId());
//        training.setQuestions(questions);

        List<Question> questions = training.getQuestions();
        questions.forEach(questionRepository::save);
        questions.forEach(question -> question.setTraining(training));

        training.setTip(tip);

        return trainingRepository.save(training);
    }
}
