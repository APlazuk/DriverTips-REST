package pl.coderslab.drivertips.services;

import pl.coderslab.drivertips.model.Answer;
import pl.coderslab.drivertips.model.Question;

import java.util.List;

public interface AnswerService {
    Answer createNewAnswer(Question question, Answer answer);

    Answer findAnswerById(Long id);

    Answer getAnswerByQuestionId(Long questionId);

    Answer updateAnswer(Answer answer);

    List<Answer> getAnswersByQuestionId(Long questionId);

    void deleteAnswer(Answer answerToDelete);
}
