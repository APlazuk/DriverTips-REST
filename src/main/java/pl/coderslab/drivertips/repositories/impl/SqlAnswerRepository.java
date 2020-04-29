package pl.coderslab.drivertips.repositories.impl;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.coderslab.drivertips.model.Answer;
import pl.coderslab.drivertips.repositories.AnswerRepository;

import java.util.List;
import java.util.Optional;


@Repository
interface SqlAnswerRepository extends AnswerRepository, JpaRepository<Answer, Long> {

    @Override
    @Query(value = "SELECT * FROM answers LEFT JOIN questions q on answers.question_id = q.id LEFT JOIN training t on q.training_id = t.id LEFT JOIN tip t2 on t.tip_id = t2.id WHERE question_id = ?", nativeQuery = true)
    Optional<List<Answer>> getAnswersByQuestionId(Long questionId);
}
