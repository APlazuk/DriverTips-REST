package pl.coderslab.drivertips.repositories;


import pl.coderslab.drivertips.model.Question;

import java.util.List;
import java.util.Optional;

public interface QuestionRepository {
    List<Question> getQuestionsByTrainingId(Long id);

    Optional<List<Question>> getQuestionsByTipIdAndTrainingId(Long tipId, Long trainingId);

    Optional<Question> findQuestionById(Long id);

    Question save(Question question);

    void deleteById(Long id);
}
