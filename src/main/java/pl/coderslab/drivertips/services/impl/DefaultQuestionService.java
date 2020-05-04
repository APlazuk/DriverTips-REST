package pl.coderslab.drivertips.services.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.coderslab.drivertips.exceptions.QuestionNotFoundException;
import pl.coderslab.drivertips.model.Answer;
import pl.coderslab.drivertips.model.Multimedia;
import pl.coderslab.drivertips.model.Question;
import pl.coderslab.drivertips.model.Training;
import pl.coderslab.drivertips.repositories.AnswerRepository;
import pl.coderslab.drivertips.repositories.QuestionRepository;
import pl.coderslab.drivertips.services.MultimediaService;
import pl.coderslab.drivertips.services.QuestionService;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class DefaultQuestionService implements QuestionService {

    private final QuestionRepository questionRepository;
    private final AnswerRepository answerRepository;
    private final MultimediaService multimediaService;

    public DefaultQuestionService(QuestionRepository questionRepository, AnswerRepository answerRepository, MultimediaService multimediaService) {
        this.questionRepository = questionRepository;
        this.answerRepository = answerRepository;
        this.multimediaService = multimediaService;
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
        List<Multimedia> media = multimediaService.getMultimedia(multimedia);


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
    public Question updateQuestion(Question question) {
        return questionRepository.save(question);
    }

    @Override
    public void deleteQuestion(Question question) {
        questionRepository.deleteById(question.getId());
    }
}
