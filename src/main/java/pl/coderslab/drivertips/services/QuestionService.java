package pl.coderslab.drivertips.services;

import pl.coderslab.drivertips.model.Question;
import pl.coderslab.drivertips.model.Training;

import java.util.List;

public interface QuestionService {
    List<Question> getQuestionByTrainingId(Long tipId, Long trainingId);

    Question createNewQuestion(Question question, Training training);

    Question findQuestionById(Long id);

    Question updateQuestion(Question question);

    void deleteQuestion(Question question);
}
