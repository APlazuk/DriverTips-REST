package pl.coderslab.drivertips.services;

import pl.coderslab.drivertips.model.Answer;

import java.util.List;

public interface AnswerService {
    List<Answer> getAnswersByQuestionId(Long questionId);
}
