package pl.coderslab.drivertips.repositories;

import pl.coderslab.drivertips.model.Answer;

import java.util.List;
import java.util.Optional;

public interface AnswerRepository {

    Answer save(Answer answer);

    Optional<List<Answer>> getAnswersByQuestionId(Long questionId);
}
