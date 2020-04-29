package pl.coderslab.drivertips.services.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.coderslab.drivertips.exceptions.MultimediaAlreadyInUseException;
import pl.coderslab.drivertips.exceptions.MultimediaNotFoundException;
import pl.coderslab.drivertips.exceptions.QuestionNotFoundException;
import pl.coderslab.drivertips.model.Answer;
import pl.coderslab.drivertips.model.Multimedia;
import pl.coderslab.drivertips.model.Question;
import pl.coderslab.drivertips.model.Training;
import pl.coderslab.drivertips.repositories.AnswerRepository;
import pl.coderslab.drivertips.repositories.MultimediaRepository;
import pl.coderslab.drivertips.repositories.QuestionRepository;
import pl.coderslab.drivertips.services.QuestionService;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Optional;

@Service
@Transactional
public class DefaultQuestionService implements QuestionService {

    private final QuestionRepository questionRepository;
    private final MultimediaRepository multimediaRepository;
    private final AnswerRepository answerRepository;

    public DefaultQuestionService(QuestionRepository questionRepository, MultimediaRepository multimediaRepository, AnswerRepository answerRepository) {
        this.questionRepository = questionRepository;
        this.multimediaRepository = multimediaRepository;
        this.answerRepository = answerRepository;
    }

    @Override
    public List<Question> getQuestionByTrainingId(Long tipId, Long trainingId) {
        Optional<List<Question>> questionByTipIdAndTrainingId = questionRepository.getQuestionsByTipIdAndTrainingId(tipId, trainingId);

        if (questionByTipIdAndTrainingId.isEmpty()) {
            throw new QuestionNotFoundException(String.format("Pytanie dla danego id porady: %s oraz treningu: %s nie istnieje", tipId, trainingId));
        }

        return questionByTipIdAndTrainingId.get();
    }

    @Override
    public Question createNewQuestion(Question question, Training training) {
        List<Multimedia> multimedia = question.getMultimedia();
        ListIterator<Multimedia> multimediaListIterator = multimedia.listIterator();

        List<Multimedia> media = new ArrayList<>();
        if (multimediaListIterator.hasNext()) {
            Multimedia next = multimediaListIterator.next();
            Optional<Multimedia> mediaFromDB = multimediaRepository.findMultimediaById(next.getId());

            if (mediaFromDB.isEmpty()) {
                throw new MultimediaNotFoundException(String.format("Medium o danym id: '%s' nie zostało znalezione", mediaFromDB.get().getId()));
            }else {
                if (!multimediaRepository.isMediaAlreadyInUse(mediaFromDB.get().getId())){
                    throw new MultimediaAlreadyInUseException(String.format("Medium o danym id: '%s' jest już używane przez inny obiekt", mediaFromDB.get().getId()));
                }
            }
            media.add(mediaFromDB.get());
        }


        List<Answer> answers = question.getAnswers();
        answers.forEach(answerRepository::save);
        answers.forEach(answer -> answer.setQuestion(question));


        question.setMultimedia(media);
        question.setTraining(training);

        return questionRepository.save(question);
    }

    @Override
    public Question findQuestionById(Long id) {
        Optional<Question> questionFromDB = questionRepository.findQuestionById(id);

        if (questionFromDB.isEmpty()) {
            throw new QuestionNotFoundException(String.format("Pytanie o danym id: %s nie istnieje", id));
        }

        return questionFromDB.get();
    }

    @Override
    public Question updateQuestion(Long id, Question question) {
        Optional<Question> questionFromDB = questionRepository.findQuestionById(id);

        if (questionFromDB.isEmpty()){
            throw new QuestionNotFoundException(String.format("Pytanie o danym id: %s nie istnieje", id));
        }

        return questionFromDB.get();
    }

    @Override
    public void deleteQuestion(Question question) {
        questionRepository.deleteById(question.getId());
    }
}
