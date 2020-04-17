package pl.coderslab.drivertips.repositories.impl;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.coderslab.drivertips.model.Answer;
import pl.coderslab.drivertips.repositories.AnswerRepository;


@Repository
interface DefaultAnswerRepository extends AnswerRepository, JpaRepository<Answer, Long> {
}
