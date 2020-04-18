package pl.coderslab.drivertips.repositories.impl;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.coderslab.drivertips.model.Question;
import pl.coderslab.drivertips.repositories.QuestionRepository;

import java.util.List;

@Repository
interface SqlQuestionRepository extends QuestionRepository, JpaRepository<Question, Long> {

    @Override
    @Query(value = "SELECT questions.id, points, text FROM questions JOIN training t on questions.training_id = t.id WHERE t.id =?", nativeQuery = true)
    List<Question> getQuestionsByTrainingId(Long id);
}
