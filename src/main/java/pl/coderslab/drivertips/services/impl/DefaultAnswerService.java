package pl.coderslab.drivertips.services.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.coderslab.drivertips.exceptions.AnswerNotFoundException;
import pl.coderslab.drivertips.model.Answer;
import pl.coderslab.drivertips.model.Multimedia;
import pl.coderslab.drivertips.model.Question;
import pl.coderslab.drivertips.repositories.AnswerRepository;
import pl.coderslab.drivertips.services.AnswerService;
import pl.coderslab.drivertips.services.MultimediaService;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class DefaultAnswerService implements AnswerService {

    private final AnswerRepository answerRepository;
    private final MultimediaService multimediaService;

    public DefaultAnswerService(AnswerRepository answerRepository, MultimediaService multimediaService) {
        this.answerRepository = answerRepository;
        this.multimediaService = multimediaService;
    }

    @Override
    public List<Answer> getAnswersByQuestionId(Long questionId) {

        Optional<List<Answer>> answersByQuestionId = answerRepository.getAnswersByQuestionId(questionId);

        if (answersByQuestionId.isEmpty()) {
            throw new AnswerNotFoundException(String.format("Odpowiedź dla danego id pytania: %s nie istnieje", questionId));
        }

        return answersByQuestionId.get();
    }

    @Override
    public void deleteAnswer(Answer answerToDelete) {
        answerRepository.deleteById(answerToDelete.getId());
    }

    @Override
    public Answer getAnswerByQuestionId(Long questionId) {
        Optional<Answer> answerFromDB = answerRepository.findAnswerByQuestionId(questionId);

        if (answerFromDB.isEmpty()) {
            throw new AnswerNotFoundException(String.format("Odpowiedź dla danego id pytania: %s nie istnieje", questionId));
        }

        return answerFromDB.get();
    }

    @Override
    public Answer updateAnswer(Answer answer) {
        return answerRepository.save(answer);
    }

    @Override
    public Answer createNewAnswer(Question question, Answer answer) {
        List<Multimedia> multimedia = answer.getMultimedia();
        List<Multimedia> media = multimediaService.getMultimedia(multimedia);

        answer.setMultimedia(media);
        answer.setQuestion(question);

        return answerRepository.save(answer);
    }

    @Override
    public Answer findAnswerById(Long id) {
        Optional<Answer> answerFromDB = answerRepository.findAnswerById(id);

        if (answerFromDB.isEmpty()) {
            throw new AnswerNotFoundException(String.format("Odpowiedź dla danego id: %s nie istnieje", id));
        }

        return answerFromDB.get();
    }
}
