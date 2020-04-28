package pl.coderslab.drivertips.services.impl;

import org.springframework.stereotype.Service;
import pl.coderslab.drivertips.exceptions.MultimediaNotFoundException;
import pl.coderslab.drivertips.exceptions.QuestionNotFoundException;
import pl.coderslab.drivertips.model.Multimedia;
import pl.coderslab.drivertips.model.Question;
import pl.coderslab.drivertips.model.Training;
import pl.coderslab.drivertips.repositories.MultimediaRepository;
import pl.coderslab.drivertips.repositories.QuestionRepository;
import pl.coderslab.drivertips.services.QuestionService;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Optional;

@Service
public class DefaultQuestionService implements QuestionService {

    private final QuestionRepository questionRepository;
    private final MultimediaRepository multimediaRepository;

    public DefaultQuestionService(QuestionRepository questionRepository, MultimediaRepository multimediaRepository) {
        this.questionRepository = questionRepository;
        this.multimediaRepository = multimediaRepository;
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

        Optional<Multimedia> mediaFromDB = Optional.empty();
        if (multimediaListIterator.hasNext()) {
            Multimedia next = multimediaListIterator.next();
            mediaFromDB = multimediaRepository.findMultimediaById(next.getId());
        }

        if (mediaFromDB.isEmpty()) {
            throw new MultimediaNotFoundException(String.format("Medium o danym id: '%s' nie zostało znalezione", mediaFromDB.get().getId()));
        }

        List<Multimedia> media = new ArrayList<>();
        media.add(mediaFromDB.get());

        question.setMultimedia(media);
        question.setTraining(training);

        return questionRepository.save(question);
    }
}
