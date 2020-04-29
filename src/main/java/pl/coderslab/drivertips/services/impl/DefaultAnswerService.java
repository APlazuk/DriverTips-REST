package pl.coderslab.drivertips.services.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.coderslab.drivertips.exceptions.AnswerNotFoundException;
import pl.coderslab.drivertips.exceptions.TipNotFoundException;
import pl.coderslab.drivertips.model.Answer;
import pl.coderslab.drivertips.repositories.AnswerRepository;
import pl.coderslab.drivertips.services.AnswerService;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class DefaultAnswerService implements AnswerService {

    private final AnswerRepository answerRepository;

    public DefaultAnswerService(AnswerRepository answerRepository) {
        this.answerRepository = answerRepository;
    }

    @Override
    public List<Answer> getAnswersByQuestionId(Long questionId) {

        Optional<List<Answer>> answersByQuestionId = answerRepository.getAnswersByQuestionId(questionId);

        if (answersByQuestionId.isEmpty()) {
            throw new AnswerNotFoundException(String.format("Odpowiedź dla danego id pytania: %s nie istnieje", questionId));
        }

        return answersByQuestionId.get();
    }
}
