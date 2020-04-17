package pl.coderslab.drivertips.repositories.impl;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.coderslab.drivertips.model.Question;
import pl.coderslab.drivertips.repositories.QuestionRepository;

@Repository
interface DefaultQuestionRepository extends QuestionRepository, JpaRepository<Question, Long> {
}
