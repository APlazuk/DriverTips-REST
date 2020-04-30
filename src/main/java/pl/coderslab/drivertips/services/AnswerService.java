package pl.coderslab.drivertips.services;

import pl.coderslab.drivertips.model.Answer;
import pl.coderslab.drivertips.model.Question;

import java.util.List;

public interface AnswerService {
    List<Answer> getAnswersByQuestionId(Long questionId);

    Answer getAnswerByQuestionId(Long questionId);

    Answer createNewAnswer(Question question, Answer answer);
}
