package pl.coderslab.drivertips.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.coderslab.drivertips.domain.Question;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {
}
