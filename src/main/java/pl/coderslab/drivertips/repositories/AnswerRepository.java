package pl.coderslab.drivertips.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.coderslab.drivertips.domain.Answer;


@Repository
public interface AnswerRepository extends JpaRepository<Answer, Long> {
}
