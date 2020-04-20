package pl.coderslab.drivertips.repositories.impl;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.coderslab.drivertips.model.Question;
import pl.coderslab.drivertips.repositories.QuestionRepository;

import java.util.List;
import java.util.Optional;

@Repository
interface SqlQuestionRepository extends QuestionRepository, JpaRepository<Question, Long> {

    @Override
    @Query(value = "SELECT questions.id, points, text FROM questions JOIN training t on questions.training_id = t.id WHERE t.id =?", nativeQuery = true)
    List<Question> getQuestionsByTrainingId(Long id);

    @Override
    @Query(value = "SELECT * FROM questions JOIN training t on questions.training_id = t.id JOIN tip t2 on t.tip_id = t2.id WHERE t2.id =:tipId AND t.id =:trainingId", nativeQuery = true)
    Optional<List<Question>> getQuestionsByTipIdAndTrainingId(Long tipId, Long trainingId);
}
