package pl.coderslab.drivertips.repositories;


import pl.coderslab.drivertips.model.Question;

import java.util.List;

public interface QuestionRepository {
    Question save(Question question);

    List<Question> getQuestionsByTrainingId(Long id);
}
