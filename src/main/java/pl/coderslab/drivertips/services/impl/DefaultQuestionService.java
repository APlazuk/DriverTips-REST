package pl.coderslab.drivertips.services.impl;

import org.springframework.stereotype.Service;
import pl.coderslab.drivertips.exceptions.QuestionNotFoundException;
import pl.coderslab.drivertips.model.Question;
import pl.coderslab.drivertips.model.Training;
import pl.coderslab.drivertips.repositories.QuestionRepository;
import pl.coderslab.drivertips.services.QuestionService;

import java.util.List;
import java.util.Optional;

@Service
public class DefaultQuestionService implements QuestionService {

    private final QuestionRepository questionRepository;

    public DefaultQuestionService(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    @Override
    public List <Question> getQuestionByTrainingId(Long tipId, Long trainingId) {
        Optional<List<Question>> questionByTipIdAndTrainingId = questionRepository.getQuestionsByTipIdAndTrainingId(tipId,trainingId);

        if (questionByTipIdAndTrainingId.isEmpty()){
            throw new QuestionNotFoundException(String.format("Pytanie dla danego id porady: %s oraz treningu: %s nie istnieje", tipId,trainingId));
        }

        return questionByTipIdAndTrainingId.get();
    }

    @Override
    public Question createNewQuestion(Question question, Training training) {
        question.setTraining(training);

        return questionRepository.save(question);

    }
}
